package com.MoMo.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MoMo.dto.MenuDto;
import com.MoMo.dto.MenuDtoList;
import com.MoMo.dto.OwnerDto;
import com.MoMo.service.CityService;
import com.MoMo.service.OwnerService;

@Controller
public class OwnerController {
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private CityService cityService;

	// 사업자 신원 조회 후 등록
	@PostMapping("/ownerinsert")
	public String ownerInsert(OwnerDto ownerDto, Principal principal) {
		if (principal.getName().equals("") || principal.getName() == null) {
			return "redirect:/mypage";
		}
		ownerService.setPermit(principal.getName(),5);
		ownerDto.setUser_id(principal.getName());
		ownerService.insertOwner(ownerDto);
		return "redirect:/mypage";
	}

	// 사업자 홈페이지 form
	@GetMapping("/owner_store_form")
	public String storeFormPage(@RequestParam("owner_eid") String owner_eid, Model model) {
		OwnerDto ownerDto = ownerService.selectOneByEidFromOwner(owner_eid);
		System.out.println(ownerDto.toString());
		HashMap<String, Object> cate = ownerService.selectCateName(ownerDto.getStore_category());
		String category = "";
		if(cate != null) {
			category += cate.get("category1")+">"+cate.get("category2");
		}
		model.addAttribute("cate", category);
		model.addAttribute("ownerDto", ownerDto);

		// 수정을 위한 메뉴
		List<MenuDto> menus = ownerService.selectAllMenuByEidFromMenu(owner_eid);
		model.addAttribute("menus", menus);
		return "store/storeForm";
	}

	// 사업자 홈페이지 form 등록
	@PostMapping("/owner_store_form")
	public String storeFormInsert(OwnerDto ownerDto,@RequestParam("category")String category ,@ModelAttribute(value = "menuDto") MenuDtoList menuDtos) {
		String path = "D:\\koreait\\JAVA_workSpace\\momo\\src\\main\\resources\\photo\\";
		// 홈페이지 수정
		System.out.println(ownerDto.toString());
		System.out.println(category);
		String[] cate = category.split(">");
		//카테고리 등록
		int cate_no = ownerService.selectCateNo(cate[0].strip(), cate[1].strip());
		ownerDto.setStore_category(cate_no);
		ownerService.updateStore(ownerDto);
		
		
//		ownerService.updateStore(ownerDto);
//		List<MenuDto> menu_list = menuDtos.getMenuDtos();
//
//		// 메뉴 등록
//		// 메뉴가 있으면,
//		if (menuDtos != null) {
//			// 메뉴 리스트 길이만큼 반복
//
//			menu_list.forEach((menu) -> {
//				menu.setOwner_eid(ownerDto.getOwner_eid());
//				// 이미지가 있는지 확인 현재 이미지를 바꾸거나 새로 만들면 이미지가 있음
//				if (menu.getMultfile() != null) {
//					// 이미지를 바꿨는지 판별 null이면 안 바꾼 것
//					if (!(menu.getMultfile().getOriginalFilename().equals(""))) {
//						// 이미지를 업로드 해야하는 경우
//						menu.setPhoto_org_name(menu.getMultfile().getOriginalFilename());
//						menu.setPhoto_name(UUID.randomUUID().toString().substring(0, 8) + "_"
//								+ menu.getMultfile().getOriginalFilename());
//						menu.setPhoto_path(path);
//						// 이미지 업로드
//						try {
//							menu.getMultfile().transferTo(new File(menu.getPhoto_path() + menu.getPhoto_name()));
//						} catch (IllegalStateException | IOException e) {
//							e.printStackTrace();
//						}
//					}
//					// 이미지가 바뀌지 않았을 경우
//				} else {
//					MenuDto dmenu = ownerService.selectOneByMenuNoFromMenu(menu.getMenu_no());
//					menu.setPhoto_org_name(dmenu.getPhoto_org_name());
//					menu.setPhoto_name(dmenu.getPhoto_name());
//					menu.setPhoto_path(dmenu.getPhoto_path());
//				}
//				// 수정인지, 삽입인지 확인
//				if (menu.getMenu_no() != 0) {
//					// 0이 아니면 수정
//					ownerService.updateMenu(menu);
//				} else {
//					// 0이면 삽입
//					ownerService.insertMenu(menu);
//				}
//
//			});
//
//		}
		return "redirect:/mypage";
	}

	@ResponseBody
	@PostMapping("/insert_category")
	public String update_owner(@RequestParam(value = "category", defaultValue = "") String category) {
		System.out.println("입력된 카테고리는 : " + category);
		String[] cate = category.split(">");
		cate[0] = cate[0].strip();
		cate[1] = cate[1].strip();
		System.out.println("db에 등록되어 있는 카테고리인지 확인합니다.");
		if (ownerService.checkCate(cate[0],cate[1]) == 0) {
			System.out.println("등록이 안되어 있기때문에 등록을 실시 합니다.");
			// 없으면 등록
			ownerService.insertCate(cate[0], cate[1]);
			System.out.println("등록 성공..");
		} else {
			System.out.println("이미 등록되어 있는 카테고리 입니다.");
		}
		return null;
	}

	@ResponseBody
	@GetMapping("/delete/{tbl_name}")
	public void delete(@PathVariable("tbl_name") String tbl_name, @RequestParam("col") String col,
			@RequestParam("val") String val) {
		String tbl = "tbl_" + tbl_name;
		ownerService.delete(tbl, col, val);
	}

	@ResponseBody
	@PostMapping("/insert_menu")
	public MenuDto insert_menu(MenuDto menuDto) {
		MenuDto flag = null;
		String path = "D:\\koreait\\JAVA_workSpace\\momo\\src\\main\\resources\\photo\\";
		menuDto.setPhoto_path(path);
		String org_name = menuDto.getMultfile().getOriginalFilename();
		String uuid = UUID.randomUUID().toString().substring(0, 8);
		menuDto.setPhoto_name(uuid + "_" + org_name);
		menuDto.setPhoto_org_name(org_name);
		try {
			menuDto.getMultfile().transferTo(new File(menuDto.getPhoto_path() + menuDto.getPhoto_name()));
			ownerService.insertMenu(menuDto);
			MenuDto menu = ownerService.selectOneMenuByPhotoName(menuDto.getPhoto_name());
			flag = menu;
			return flag;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@ResponseBody
	@PostMapping("/getcategory")
	public List<String> getcate(@RequestParam("filter1") String cate1){
		List<String> cate2 = ownerService.getcate2(cate1);
		return cate2;
	}
	
	@ResponseBody
	@PostMapping("/delete_member")
	public void delete_member(@RequestParam("user_id") String user_id) {
		ownerService.delete("tbl_member", "user_id", user_id);
	}
	//테스트 사업자 임시 등록
	@GetMapping("/insert_test")
	public String ddd() {
		List<String> city_list = cityService.selectDCTCity();
		city_list.forEach(city->{
			for(int i = 0; i<30;i++) {
				String eid = UUID.randomUUID().toString().substring(0,8);
				OwnerDto dto = new OwnerDto();
				dto.setOwner_eid(eid);
				dto.setUser_id("test");
				dto.setOwner_bm("삼식이식당"+String.valueOf(i));
				dto.setOwner_addr(city);
				dto.setStore_tel("010-0000-0000");
				System.out.println(dto.toString());
				ownerService.insertOwner(dto);
			}
		});
		
		return "index";
	}
	
}
