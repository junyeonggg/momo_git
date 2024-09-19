package com.MoMo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.MoMo.dto.EmailDto;
@Service
public interface EmailService {
	
	@Async
	boolean sendMail(EmailDto emailDto);

}
