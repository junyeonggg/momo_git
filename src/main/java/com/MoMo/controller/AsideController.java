package com.MoMo.controller;

import java.util.List;

import com.MoMo.service.CityService;

@Controller
public class AsideController {
	@Autowired
	private CityService cityService;
	
	@ResponseBody
	@PostMapping("/si_gun_gu") 
	public List<String> si_gun_gu(Model model,@RequestParam("city") String city) {
		List<String> si_gun_gu_list = cityService.selectSiGunGuByCity(city);
		return si_gun_gu_list;
	}
}
