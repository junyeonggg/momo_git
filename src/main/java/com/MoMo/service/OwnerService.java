package com.MoMo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.MoMo.dto.CityDto;
import com.MoMo.dto.MenuDto;
import com.MoMo.dto.OwnerDto;
import com.MoMo.paging_search.Paging;

@Service
public interface OwnerService {
	//한번쓰고 안쓸 메서드 ( 행정구역 추가 )
	void insertCitys(CityDto city);
	
	void insertOwner(OwnerDto ownerDto);

	List<OwnerDto> selectAllByUserIdFromOwner(String user_id);

	OwnerDto selectOneByEidFromOwner(String owner_eid);

	void updateStore(OwnerDto ownerDto);

	List<OwnerDto> selectAllStore();

	void insertMenu(MenuDto menu);

	List<MenuDto> selectAllMenuByEidFromMenu(String eid);

	MenuDto selectOneByMenuNoFromMenu(int menu_no);



	List<OwnerDto> selectAllStoreBySiDo(CityDto si_do,Paging paging);

	int selectAllCountStoreBySiDo(CityDto si_do,String keyword,String category_name);

	void delete(String tbl, String col, String val);

	void updateMenu(MenuDto menu);

	List<OwnerDto> selectLikeStoresByUserId(String name,Paging paging);

	int selectLikeStoresCntByUserId(String name);

	void increView(String owner_eid, int i);

	// 서울경기인천
	List<OwnerDto> selectSG(String string, String string2, String string3);

	//강원도
	List<OwnerDto> selectG(String string);

	//충청 대전 세종
	List<OwnerDto> selectCDS(String string, String string2, String string3);

	//경상도,울산,부산,대구
	List<OwnerDto> selectGUBD(String string, String string2, String string3, String string4);

	//전라,광주
	List<OwnerDto> selectGGG(String string, String string2, String string3);

	MenuDto selectOneMenuByPhotoName(String photo_name);

	int checkCate(String string, String string2);

	void insertCate(String string, String string2);

	int selectCateNo(String string, String string2);

	List<String> selectCate1();

	List<String> getcate2(String cate1);

	List<OwnerDto> selectrandom(String city, String category);

	void setPermit(String name, int i);

	HashMap<String, Object> selectCateName(int category_no);
	
}
