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
public class OwnerDto {
	private String owner_eid;
	private String user_id;
	private String owner_bm; //상호명
	private String owner_addr; //주소
	private String store_tel;
	private String store_addr;
	private String store_place_no;
	private int store_view;
//	private int category_no;
	private int store_category;
	private String category2;
}
