package com.MoMo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MoMo.dto.MemberDto;
import com.MoMo.service.EmailService;
import com.MoMo.service.MemberService;

@Controller
public class JoinController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	//회원가입 페이지1
//	@GetMapping("/join")
//	public String joinPage() {
//		return "join/joinRole";
//	}
//	
//	//회원가입 페이지2
//	@GetMapping("/joinForm")
//	public String joinFormPage(@RequestParam("type")String user_type,Model model) {
//		System.out.println(user_type);
//		model.addAttribute("user_type", user_type);
//		return "join/joinPage";
//	}
	//위 코드 수정 본
	//회원가입 페이지 통합
	@GetMapping("/join")
	public String joinFormPage() {
		return "join/joinPage";
	}	
	
	
	
	// 아이디 중복 확인
	@ResponseBody
	@PostMapping("/join/userIdCheck")
	public boolean userIdCheck(@RequestParam("userId") String user_id) {
		boolean existId = false;
		existId = memberService.selectObjByUserId(user_id) == null ? false:true;
		return existId;
	}
	
	
	//회원가입 실시
//	@PostMapping("/join")
//	public String join(MemberDto memberDto, OwnerDto ownerDto) {
//		//permit이 5일 경우 ownerDto도 같이 저장한다.
//		if(memberDto.getUser_permit() == 5) {
//			memberService.insertOwner(ownerDto);
//		}
//		memberService.insertMember(memberDto);
//		return "redirect:/login";
//	}
	//위 코드 수정 (why? 매장추가를 따로 분리 )
	//시큐리티 추가해야 함
	@PostMapping("/join")
	public String join(MemberDto memberDto) {
		System.out.println(memberDto.toString());
		MemberDto checkuser = memberService.selectObjByUserId(memberDto.getUser_id());
		//신규 회원, 또는 기존 회원 정보 수정
		if (checkuser == null) {
			System.out.println("신규회원");
			memberDto.setUser_pw(passwordEncoder.encode(memberDto.getUser_pw()));
			memberService.insertMember(memberDto);
		}else {
			System.out.println("기존회원");
			memberService.updateInfoset(memberDto);
			return "redirect:/mypage";
		}
		
		return "redirect:/login";
	}
	
	

	//이메일 인증하는 부분은 비밀번호/아이디 찾기 부분으로 넘길 예정
//	@ResponseBody
//	@PostMapping("/emailCheck")
//	public String emailCheck(@RequestParam("user_email")String user_email) {
//		boolean flag = false;
//		String subject = "<MoMo> 이메일 인증 번호";
//		String uuid = UUID.randomUUID().toString().substring(0,8);
//		String text = uuid;
//		System.out.println(text);
//		EmailDto emailDto = new EmailDto(null,user_email,subject,text);
//		flag = emailService.sendMail(emailDto);
//		if(flag) {
//			System.out.println("전송완료");
//		}else {
//			System.out.println("전송 실패..");
//			text = "error";
//		}
//		return text;
//	}
}
