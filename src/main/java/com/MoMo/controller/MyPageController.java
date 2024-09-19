package com.MoMo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MoMo.dto.MemberDto;
import com.MoMo.dto.OwnerDto;
import com.MoMo.service.MemberService;
import com.MoMo.service.OwnerService;

@Controller
public class MyPageController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/mypage")
	public String myPage(Model model,Principal principal) {
		// 현재 로그인한 유저 정보 모델에 저장
		String user_id = principal.getName();
		MemberDto memberDto = memberService.updateInfo(user_id);
		model.addAttribute("memberDto", memberDto);
		
		//현재 로그인한 유저의 매장 정보 저장
		List<OwnerDto> ownerDto = ownerService.selectAllByUserIdFromOwner(user_id);
		model.addAttribute("ownerDto", ownerDto);
		
		// 관리자를 위한 유저 정보 리스트
		List<MemberDto> members = memberService.selectAll();
		model.addAttribute("members", members);
		
		return "member/mypage";
	}
	//비밀번호 수정 팝업 창
	@GetMapping("/updatePw")
	public String updatePw() {
		return "member/updatePwPop";
	}
	@ResponseBody
	@PostMapping("/currentPwCheck")
	public boolean checkPw(@RequestParam("cPw") String cPw, Principal principal) {
		// db에서 현재 유저의 id 가져오기
		String pw= memberService.selectObjByUserId(principal.getName()).getUser_pw();
		// 입력한 비밀번호와 비교하기하여 반환
		return passwordEncoder.matches(cPw, pw);
	}
	//비밀번호 수정
	@ResponseBody
	@PostMapping("/updatePw")
	public boolean updatedPw(Principal principal,@RequestParam("user_pw")String user_pw) {
		boolean flag = false;
		try {
			memberService.updateOneColumn(principal.getName(),"user_pw",passwordEncoder.encode(user_pw));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
