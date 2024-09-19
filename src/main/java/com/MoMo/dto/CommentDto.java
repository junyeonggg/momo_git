package com.MoMo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
public class CommentDto {
	private int comment_no;
	private String comment_content;
	private String comment_create_date;
	private String user_id;
	private String owner_eid;
	private String mention_title;
	private int type;
	public CommentDto() {
		 LocalDate now = LocalDate.now();
		this.comment_create_date = now.toString();
	}
}
