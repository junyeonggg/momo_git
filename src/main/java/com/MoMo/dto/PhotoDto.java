package com.MoMo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {
	private int photo_no;
	private String photo_path;
	private String photo_name;
	private String photo_org_name;
	private int menu_no;
}
