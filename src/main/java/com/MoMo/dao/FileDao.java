package com.MoMo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.MoMo.dto.PhotoDto;

@Mapper
public interface FileDao {
	@Insert("insert into tbl_photo values(null,#{photo_path},#{photo_name},#{photo_org_name},#{menu_no})")
	void photoUpload(PhotoDto photo);
		
}
