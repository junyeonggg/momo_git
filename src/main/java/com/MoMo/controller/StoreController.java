package com.MoMo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoMo.dto.CityDto;
import com.MoMo.dto.CommentDto;
import com.MoMo.dto.MenuDto;
import com.MoMo.dto.OwnerDto;
import com.MoMo.paging_search.Paging;
import com.MoMo.service.CommentService;
import com.MoMo.service.LikeService;
import com.MoMo.service.OwnerService;

@Controller
public class StoreController {
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private LikeService likeService;

	// 모든 store 출력
	@GetMapping("/stores")
	public String storeAllPage(Model model, CityDto si_do,
			@RequestParam(value = "current_page", defaultValue = "1") int current_page,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword, Principal principal,
			@RequestParam(value = "recordsize", defaultValue = "15") int record_size,
			@RequestParam(value="category_name", defaultValue = "") String category_name) {
		if (si_do.getSi_gun_gu() == null)
			si_do.setSi_gun_gu("");
		int totDataCnt = ownerService.selectAllCountStoreBySiDo(si_do, keyword,category_name);
		Paging paging = new Paging(totDataCnt, record_size, 10, current_page);
		paging.setKeyword(keyword);
		paging.setCategory_name(category_name);
		List<OwnerDto> ownerDtos = si_do.getSi_do() != null ? ownerService.selectAllStoreBySiDo(si_do, paging)
				: ownerService.selectAllStore();
		System.out.println(paging.toString());
		model.addAttribute("stores", ownerDtos);
		model.addAttribute("paging", paging);
		model.addAttribute("si_do", si_do);

		// 관심매장 확인
		try {
			List<String> likeStoreList = likeService.selectLikeStore(principal.getName());
			model.addAttribute("likeStores", likeStoreList);
		} catch (Exception e) {
			System.out.println("비로그인");
		}
		return "store/stores";
	}

	// 관심 store 출력
	@GetMapping("/stores/like")
	public String storelikePage(Model model, @RequestParam(value = "current_page", defaultValue = "1") int current_page,
			Principal principal) {
		int totDataCnt = ownerService.selectLikeStoresCntByUserId(principal.getName());
		Paging paging = new Paging(totDataCnt, 5, 2, current_page);
		List<OwnerDto> ownerDtos = ownerService.selectLikeStoresByUserId(principal.getName(),paging);
		model.addAttribute("stores", ownerDtos);
		model.addAttribute("paging", paging);

		// 관심매장 확인
		List<String> likeStoreList = likeService.selectLikeStore(principal.getName());
		model.addAttribute("likeStores", likeStoreList);
		return "store/storesLike";
	}

	// store 하나
	@GetMapping("/store")
	public String storeOnePage(Model model, @RequestParam("store") String eid, Principal principal) throws Exception {
		// 홈페이지 기본 정보
		OwnerDto ownerDto = ownerService.selectOneByEidFromOwner(eid);
		//조회수
		int view = ownerDto.getStore_view();
		ownerService.increView(ownerDto.getOwner_eid(),view+1);
		
		
		model.addAttribute("store", ownerDto);
		// 홈페이지 메뉴 정보 ( 다운로드 )
		List<MenuDto> menuDto = ownerService.selectAllMenuByEidFromMenu(eid);
		model.addAttribute("menus", menuDto);
		try {
			if (principal.getName() != null) {
				model.addAttribute("user", principal.getName());
			}
		} catch (Exception e) {
			System.out.println("비로그인");
		}

		// 댓글 리스트
		List<CommentDto> dtos = commentService.selectCommentByEid(eid);
		List<CommentDto> mentionDtos = new ArrayList<CommentDto>();
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		dtos.forEach((dto)->{
			if(dto.getType()==1) mentionDtos.add(dto);
			if(dto.getType()==0) commentDtos.add(dto);
		});
		model.addAttribute("mentions", mentionDtos);
		model.addAttribute("comments", commentDtos);

		return "store/store";
	}

	@GetMapping("/downloadMenuImg")
	public ResponseEntity<Resource> downloadMenuImg(@RequestParam("menu_no") int menu_no) {
		try {
			MenuDto menu = ownerService.selectOneByMenuNoFromMenu(menu_no);
			Path menuImgPath = Paths.get(menu.getPhoto_path()).resolve(menu.getPhoto_name()).normalize();

			Resource resource = new UrlResource(menuImgPath.toUri());
			if (resource.exists()) {
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION,
								"attachment; filename=\"" + URLEncoder.encode(menu.getPhoto_org_name(), "UTF-8") + "\"")
						.body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/insertdb_city")
	public String ii() {
		try {
//			String path = "C:\\Users\\admin\\Desktop\\city";
			String path = "D:\\koreait\\JAVA_workSpace\\momo\\src\\main\\resources\\data\\zipcode_CSV";
			File file = new File(path);
			// listFiles함수를 통해 하위 파일들을 가져온다.
			int countAll = 0;
			for (File f_path : file.listFiles()) {
				System.out.println("진행 : ..." + f_path);
				File f = new File(f_path.toString());
				// 입력 스트림
				FileReader fileReader = new FileReader(f);
				BufferedReader bfReader = new BufferedReader(fileReader);
				String line = "";
				int count = 0;
				while ((line = bfReader.readLine()) != null) {
					// 첫번째 행을 가져오지 않기 위한 코드
					if (!line.startsWith(",우편번호")) {
						// 리스트로 split할때 컬럼이 없으면 data_list에 저장되지 않는 문제를 해결하기 위해
						// replace함수를 이용하여 임의의 값을 주고 나중에 제거
						line = line.replace(",", "-,-");
						String[] data_list = line.split(",");
						CityDto city = new CityDto(data_list[1], data_list[2], data_list[3], data_list[4], data_list[5],
								data_list[6]);
						// 임의값 제거 메서드
						city = city.reCity(city);
						ownerService.insertCitys(city);
						count++;
						countAll++;
						// db에 추가
					}
				}
				System.out.println("완료 데이터 갯수 : " + count);
				System.out.println("city 추가 완료, 전체 데이터 갯수 : " + countAll);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}
}
