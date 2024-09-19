package com.MoMo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoMo.dao.CityDao;
import com.MoMo.service.CityService;
@Service
public class CityServiceImpl implements CityService{
	@Autowired
	private CityDao cityDao;


	@Override
	public List<String> selectDCTCity() {
		return cityDao.selectDCTCity();
	}


	//시/군/구 정보 받아오기
	@Override
	public List<String> selectSiGunGuByCity(String city) {
		return cityDao.selectSiGunGuByCity(city);
	}

}
