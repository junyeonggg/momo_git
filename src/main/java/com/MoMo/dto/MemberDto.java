package com.MoMo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class MemberDto {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_addr;
	private String user_email;
	private String user_tel;
	private String user_birth;
	private String user_gender;
	private int user_permit;
}
