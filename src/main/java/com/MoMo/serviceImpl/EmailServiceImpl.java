package com.MoMo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.MoMo.dto.EmailDto;
import com.MoMo.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public boolean sendMail(EmailDto dto) {
		boolean flag = true;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(dto.getSubject());
		message.setTo(dto.getTo());
		message.setText(dto.getText());
		System.out.println(message.toString());
		try {
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

}
