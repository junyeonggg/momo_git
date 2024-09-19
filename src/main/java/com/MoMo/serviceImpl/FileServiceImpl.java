package com.MoMo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoMo.dao.FileDao;
import com.MoMo.dto.PhotoDto;
import com.MoMo.service.FileService;

@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private FileDao fileDao;
	
	@Override
	public void photoUpload(PhotoDto photo) {
		fileDao.photoUpload(photo);
	}

}
