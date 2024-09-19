package com.MoMo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.MoMo.dto.MemberDto;
import com.MoMo.dto.OwnerDto;

@Mapper
public interface MemberDao {
	
	// 아이디로 객체 긁어오기
	@Select("select * from tbl_member where user_id=#{user_id}")
	public MemberDto selectObjByUserId(String user_id);

	@Insert("insert into tbl_owner values(#{owner_eid},#{owner_tel},#{owner_addr},#{user_id})")
	public void insertOwner(OwnerDto ownerDto);

	@Insert("insert into tbl_member values(#{user_id},#{user_pw},#{user_name},#{user_addr},#{user_email},#{user_tel},#{user_birth},#{user_gender},#{user_permit})")
	public void insertMember(MemberDto memberDto);

	//멤버 정보 업데이트
	@Update("update tbl_member set user_name=#{user_name},user_addr=#{user_addr},user_tel=#{user_tel},user_birth=#{user_birth},user_gender=#{user_gender} ")
	public void updateMemberByUserId(MemberDto memberDto);
	
	//멤버 테이블에서 컬럼 하나 변경
//	@Update("update tbl_member set #{col_name}=#{value} where user_id=#{user_id}")
	@Update("update tbl_member set user_pw=#{value} where user_id=#{user_id}")
	public void updateOneByColumn(@Param("user_id") String user_id ,@Param("col_name") String col_name,@Param("value") String value);

	@Select("select user_id from tbl_member where user_name=#{user_name} and user_email=#{user_email} and user_tel = #{user_tel} ")
	public String findId(MemberDto memberDto);

	// 비밀번호 찾기
	@Select("select * from tbl_member where user_name=#{user_name} and user_email=#{user_email} and user_tel = #{user_tel} and user_id=#{user_id}")
	public MemberDto findPw(MemberDto memberDto);

	@Select("select * from tbl_member")
	public List<MemberDto> selectAll();
}
