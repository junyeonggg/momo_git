package com.MoMo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.MoMo.service.CityService;
import com.MoMo.service.OwnerService;
@ControllerAdvice
public class ModelAdController {
	@Autowired
	private CityService cityService;
	@Autowired
	private OwnerService ownerService;
	
	// 모델 전역 처리
	@ModelAttribute("leftside")
	public List<String> leftside(){
		List<String> cities = cityService.selectDCTCity();
		return cities;
	}
	@ModelAttribute("category")
	public List<String> category(){
		List<String> categories = ownerService.selectCate1();
		return categories;
	}
}
