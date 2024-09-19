package com.MoMo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.MoMo.dto.CityDto;
import com.MoMo.dto.MenuDto;

@Mapper
public interface MenuDao {

	// eid에 해당하는 모든 메뉴 정보 가져오기 
	@Select("select * from tbl_menu where owner_eid=#{eid}")
	List<MenuDto> selectdAllMenuByEidFromMenu(String eid);

	// 이미지를 위해 메뉴 하나 가져오기
	@Select("select * from tbl_menu where menu_no=#{menu_no}")
	MenuDto selectOneByMenuNoFromMenu(int menu_no);

	// city를 db에 추가하기 위한 코드
	@Insert("insert into tbl_city values(#{zip_code},#{si_do},#{si_gun_gu},#{load_name},#{dong_h},#{dong_b})")
	void insertCitys(CityDto city);
}
