package com.MoMo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CityService {

	List<String> selectDCTCity();

	List<String> selectSiGunGuByCity(String city);

}
