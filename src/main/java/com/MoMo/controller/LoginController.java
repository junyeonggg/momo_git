package com.MoMo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MoMo.dto.EmailDto;
import com.MoMo.dto.MemberDto;
import com.MoMo.service.EmailService;
import com.MoMo.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String loginPage() {
		return "login/loginPage";
	}
	/*
	 * @PostMapping("/login") public String login() { return "index"; }
	 */
	@GetMapping("/test")
	public void test(Model model) {
		System.out.println("들어옴");
		model.addAttribute("test", "test");
	}
	// 아이디 찾기/비밀번호 찾기
	@ResponseBody
	@PostMapping("/find/{what}")
	public String findId(@PathVariable("what") String what,MemberDto memberDto) {
		if(what.equals("id")) {
			String user = memberService.findId(memberDto);
			if(user == null || user.equals("")) return "false";
			return user;
			
		}else {
			System.out.println("비번찾기 들어옴");
			MemberDto member = memberService.findPw(memberDto);
			if (member == null) {
				return "false";
			}
			//비밀번호 찾기
			EmailDto email = new EmailDto();
			email.setTo(memberDto.getUser_email());
			email.setSubject("[MoMo]에서 보낸 메일입니다.");
			String newPw = (String) UUID.randomUUID().toString().subSequence(0, 8);
			email.setText("임시비밀번호 : "+newPw);
			emailService.sendMail(email);
			memberService.updateOneColumn(member.getUser_id(),"user_pw",passwordEncoder.encode(newPw));
			return "이메일로 임시 비밀번호를 전송하였습니다.";
		}
	}
	
	
}
