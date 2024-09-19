package com.MoMo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MoMo.dto.CommentDto;

@Service
public interface CommentService {

	boolean insertComment(CommentDto commentDto);

	List<CommentDto> selectCommentByEid(String eid);

	boolean updateMention(CommentDto commentDto);

	void deleteComment(int no);

}
