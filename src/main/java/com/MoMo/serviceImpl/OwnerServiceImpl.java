package com.MoMo.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoMo.dao.MenuDao;
import com.MoMo.dao.OwnerDao;
import com.MoMo.dto.CityDto;
import com.MoMo.dto.MenuDto;
import com.MoMo.dto.OwnerDto;
import com.MoMo.paging_search.Paging;
import com.MoMo.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService{
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private MenuDao menuDao;

	//한번만 쓰는 메서드 (행정구역 db에 추가)
	@Override
	public void insertCitys(CityDto city) {
		menuDao.insertCitys(city);
		
	}

	
	//점주 등록
	@Override
	public void insertOwner(OwnerDto ownerDto) {
		ownerDao.insertOwner(ownerDto);
	}

	//오너 정보 출력
	@Override
	public List<OwnerDto> selectAllByUserIdFromOwner(String user_id) {
		return ownerDao.selectAllByUserIdFromOwner(user_id);
	}

	//게시글 정보
	@Override
	public OwnerDto selectOneByEidFromOwner(String owner_eid) {
		return ownerDao.selectOneByEidFromOwner(owner_eid);
	}

	//게시글 작성
	@Override
	public void updateStore(OwnerDto ownerDto) {
		ownerDao.updateStore(ownerDto);
	}

	// 데이터베이스에서 게시글 목록 가져오기
	@Override
	public List<OwnerDto> selectAllStore() {
		return ownerDao.selectAllStore();
	}

	//관심매장 목록 갯수
	@Override
	public int selectLikeStoresCntByUserId(String name) {
		return ownerDao.selectLikeCtnStoresByUserId(name);
	}
	
	
	//관심 매장 목록
	@Override
	public List<OwnerDto> selectLikeStoresByUserId(String name,Paging paging) {
		return ownerDao.selectLikeStoresByUserId(name,paging);
	}
	
	
	
	//메뉴 등록
	@Override
	public void insertMenu(MenuDto menu) {
		ownerDao.insertMenu(menu);
	}

	@Override
	public List<OwnerDto> selectAllStoreBySiDo(CityDto si_do,Paging paging) {
		String si_do_si_gun_gu = si_do.getSi_do()+si_do.getSi_gun_gu(); 
		return ownerDao.selectAllStoreBySiDo(si_do_si_gun_gu,paging);
	}

	// 메뉴 가져오기
	@Override
	public List<MenuDto> selectAllMenuByEidFromMenu(String eid) {
		return menuDao.selectdAllMenuByEidFromMenu(eid);
	}

	@Override
	public MenuDto selectOneByMenuNoFromMenu(int menu_no) {
		return menuDao.selectOneByMenuNoFromMenu(menu_no);
	}


	@Override
	public int selectAllCountStoreBySiDo(CityDto si_do,String keyword,String category_name) {
		String si_do_si_gun_gu = si_do.getSi_do()+si_do.getSi_gun_gu(); 
		System.out.println(si_do_si_gun_gu);
		return ownerDao.selectAllCountStoreBySiDo(si_do_si_gun_gu,keyword,category_name);
	}


	//삭제 메서드, 테이블명과 넘버를 받음
	@Override
	public void delete(String tbl,String col, String val) {
		ownerDao.delete(tbl,col,val);
	}

	//메뉴 업데이트
	@Override
	public void updateMenu(MenuDto menu) {
		ownerDao.updateMenu(menu);
	}


	// 조회수 증가
	@Override
	public void increView(String owner_eid, int i) {
		ownerDao.increView(owner_eid,i);
		
	}


	// 도시별 인기 순위
	@Override
	public List<OwnerDto> selectSG(String string, String string2, String string3) {
		return ownerDao.selectSG(string,string2,string3);
	}


	@Override
	public List<OwnerDto> selectG(String string) {
		return ownerDao.selectG(string);
	}


	@Override
	public List<OwnerDto> selectCDS(String string, String string2, String string3) {
		return ownerDao.selectCDS(string,string2,string3);
	}


	@Override
	public List<OwnerDto> selectGUBD(String string, String string2, String string3, String string4) {
		return ownerDao.selectGUBD(string,string2,string3,string4);
	}


	@Override
	public List<OwnerDto> selectGGG(String string, String string2, String string3) {
		return ownerDao.selectGGG(string,string2,string3);
	}


	@Override
	public MenuDto selectOneMenuByPhotoName(String photo_name) {
		return ownerDao.selectOneMenuByPhotoName(photo_name);
	}


	//카테고리 등록 확인
	@Override
	public int checkCate(String cate1, String cate2) {
		return ownerDao.checkCate(cate1,cate2);
	}


	@Override
	public void insertCate(String cate1, String cate2) {
		ownerDao.insertCate(cate1,cate2);
	}


	//카테고리 가져오기
	@Override
	public int selectCateNo(String string, String string2) {
		return ownerDao.selectCateNo(string,string2);
	}


	@Override
	public List<String> selectCate1() {
		return ownerDao.selectCate1();
	}


	@Override
	public List<String> getcate2(String cate1) {
		return ownerDao.getcate2(cate1);
	}


	@Override
	public List<OwnerDto> selectrandom(String city, String category) {
		return ownerDao.selelectrandom(city,category);
	}


	@Override
	public void setPermit(String name, int i) {
		ownerDao.setPermit(name,i);
	}


	@Override
	public HashMap<String, Object> selectCateName(int category_no) {
		return ownerDao.selectCateNameByNo(category_no);
	}







}
