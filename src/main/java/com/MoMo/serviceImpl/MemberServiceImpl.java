package com.MoMo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoMo.dao.MemberDao;
import com.MoMo.dto.MemberDto;
import com.MoMo.dto.OwnerDto;
import com.MoMo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDao memberDao;
	
	//tbl_member에서 userId로 데이터 가져오기
	@Override
	public MemberDto selectObjByUserId(String user_id) {
		return memberDao.selectObjByUserId(user_id);
	}

	// tbl_owner에 데이터 삽입
	@Override
	public void insertOwner(OwnerDto ownerDto) {
		memberDao.insertOwner(ownerDto);
	}

	// tbl_member에 데이터 삽입
	@Override
	public void insertMember(MemberDto memberDto) {
		memberDao.insertMember(memberDto);
	}
	
	// 마이페이지 이동 시 정보 출력을 위해
	@Override
	public MemberDto updateInfo(String user_id) {
		return memberDao.selectObjByUserId(user_id);
	}

	// 멤버 정보 수정
	@Override
	public void updateInfoset(MemberDto memberDto) {
		memberDao.updateMemberByUserId(memberDto);
		
	}

	@Override
	public void updateOneColumn(String user_id,String col_name, String value) {
		System.out.println(col_name);
		System.out.println(value);
		memberDao.updateOneByColumn(user_id,col_name,value);
	}
	
	// 아이디 찾기
	@Override
	public String findId(MemberDto memberDto) {
		return memberDao.findId(memberDto);
	}

	// 비밀번호 찾기
	@Override
	public MemberDto findPw(MemberDto memberDto) {
		return memberDao.findPw(memberDto);
	}

	@Override
	public List<MemberDto> selectAll() {
		return memberDao.selectAll();
	}
}
