package com.MoMo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityDao {

	@Select("select  DISTINCT si_do from tbl_city;")
	List<String> selectDCTCity();

	@Select("select distinct si_gun_gu from tbl_city where si_do=#{city}")
	List<String> selectSiGunGuByCity(String city);
}
