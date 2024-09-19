package com.MoMo.service;

import org.springframework.stereotype.Service;

import com.MoMo.dto.PhotoDto;

@Service
public interface FileService {

	void photoUpload(PhotoDto photo);

}
