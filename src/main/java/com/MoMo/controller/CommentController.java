package com.MoMo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MoMo.dto.CommentDto;
import com.MoMo.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@PostMapping("/commentInsert")
	public boolean commentInsert(CommentDto commentDto) {
		boolean flag = false;
		System.out.println(commentDto.toString());
		if(commentDto.getMention_title() != null) commentDto.setType(1);
        flag = commentService.insertComment(commentDto);
		return flag;
	}
	
	@ResponseBody
	@PostMapping("/update_mention")
	public boolean updateMention(CommentDto commentDto) {
		boolean flag = false;
		System.out.println(commentDto.toString());
		flag = commentService.updateMention(commentDto);
		
		return flag;
	}
	@ResponseBody
	@PostMapping("/deleteComment")
	public boolean deleteComment(@RequestParam("comment_no") int comment_no) {
		boolean flag = false;
		try {
			commentService.deleteComment(comment_no);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
