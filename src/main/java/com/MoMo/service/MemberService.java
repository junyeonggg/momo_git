package com.MoMo.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.MoMo.dto.MemberDto;
import com.MoMo.dto.OwnerDto;

@Service
public interface MemberService {
	@Async
	MemberDto selectObjByUserId(String user_id);

	@Async
	void insertOwner(OwnerDto ownerDto);

	@Async
	void insertMember(MemberDto memberDto);
	@Async
	MemberDto updateInfo(String user_id);
	
	//memberInfo 수정
	@Async
	void updateInfoset(MemberDto memberDto);
	
	
	//칼럼 하나 수정, 칼럼 명과 값 이름이 같아야 한다.
	@Async
	void updateOneColumn(String user_id,String col_name,String value);

	String findId(MemberDto memberDto);

	MemberDto findPw(MemberDto memberDto);

	List<MemberDto> selectAll();
}
