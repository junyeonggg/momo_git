package com.MoMo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoMo.dao.CommentDao;
import com.MoMo.dto.CommentDto;
import com.MoMo.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public boolean insertComment(CommentDto commentDto) {
		return commentDao.insertComment(commentDto);
	}

	@Override
	public List<CommentDto> selectCommentByEid(String eid) {
		return commentDao.selectCommentByEid(eid);
	}

	@Override
	public boolean updateMention(CommentDto commentDto) {
		return commentDao.updateMention(commentDto);
	}

	@Override
	public void deleteComment(int no) {
		commentDao.deleteComment(no);
	}

}
