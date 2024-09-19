package com.MoMo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeDao {
	//관심매장 등록
	@Insert("insert into tbl_like_store values(#{name},#{eid})")
	void checkLike(@Param("eid") String eid,@Param("name") String name);
	//관심매장 삭제
	@Delete("delete from tbl_like_store where user_id=#{name} and owner_eid=#{eid}")
	void unCheckLike(@Param("eid") String eid,@Param("name") String name);
	//관심매장 리스트
	@Select("select owner_eid from tbl_like_store where user_id=#{name}")
	List<String> selectLikeStoreByUserId(String name);

	
}
