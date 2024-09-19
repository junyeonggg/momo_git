package com.MoMo.dto;

import org.springframework.web.multipart.MultipartFile;

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
public class MenuDto {
	private int menu_no;
	private String menu_name;
	private int menu_price;
	private String menu_info;
	private String owner_eid;
	private String photo_path;
	private String photo_name;
	private String photo_org_name;
	private MultipartFile multfile;
}
