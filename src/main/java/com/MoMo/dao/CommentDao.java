package com.MoMo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.MoMo.dto.CommentDto;

@Mapper
public interface CommentDao {

	@Insert("insert into tbl_comment values(null,#{comment_content},#{comment_create_date},#{user_id},#{owner_eid},#{mention_title},#{type})")
	boolean insertComment(CommentDto commentDto);

	@Select("select * from tbl_comment where owner_eid=#{eid} order by comment_create_date desc")
	List<CommentDto> selectCommentByEid(String eid);

	@Update("update tbl_comment set comment_content=#{comment_content}, mention_title=#{mention_title} where comment_no=#{comment_no}")
	boolean updateMention(CommentDto commentDto);

	@Delete("delete from tbl_comment where comment_no = #{no}")
	void deleteComment(@Param("no")int no);

	
}
