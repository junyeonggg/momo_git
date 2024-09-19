package com.MoMo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MoMo.service.LikeService;

@Controller
public class classLikeController {
	@Autowired
	private LikeService likeService;
	
	@ResponseBody
	@PostMapping("/likeCheck")
	public void likeCheck(@RequestParam("owner_eid") String eid,Principal principal) {
		likeService.checkLike(eid,principal.getName());
	}
	//관심 제거
	@ResponseBody
	@PostMapping("/likeUnCheck")
	public void likeUnCheck(@RequestParam("owner_eid") String eid,Principal principal) {
		likeService.uncheckLike(eid,principal.getName());
	}
}
