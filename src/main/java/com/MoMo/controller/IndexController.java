package com.MoMo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.MoMo.dto.OwnerDto;
import com.MoMo.service.OwnerService;

@Controller
public class IndexController {
	@Autowired
	private OwnerService ownerService;
	@GetMapping("/")
	public String indexPage(Model model) {
		// 서울/경기
		List<OwnerDto> s_g_list = ownerService.selectSG("서울특별시","경기도","인천");
		
		// 강원도
		List<OwnerDto> g_list = ownerService.selectG("강원");
		// 충청/대전
		List<OwnerDto> c_d_s_list = ownerService.selectCDS("충청","대전","세종");
		// 경상도/울산/부산
		List<OwnerDto> g_u_b_d_ist = ownerService.selectGUBD("경상","울산","부산","대구");
		// 전라도
		List<OwnerDto> g_g_g_list = ownerService.selectGGG("전북","전라남","광주");
		// 제주도
		List<OwnerDto> gegu_list = ownerService.selectG("제주");
		model.addAttribute("s_g_list", s_g_list);
		model.addAttribute("g_list", g_list);
		model.addAttribute("c_d_s_list", c_d_s_list);
		model.addAttribute("g_u_b_d_ist", g_u_b_d_ist);
		model.addAttribute("g_g_g_list", g_g_g_list);
		model.addAttribute("gegu_list", gegu_list);
		return "index";
	}
	

}
