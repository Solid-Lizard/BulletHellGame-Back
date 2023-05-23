package com.bulelthell.game.bullethellbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bulelthell.game.bullethellbackend.rest.EmailRequestRest;
import com.bulelthell.game.bullethellbackend.service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	public EmailRequestRest sendEmail(EmailRequestRest content) {		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("bullethellgameserver@gmail.com");
		message.setTo("santi.lopar@gmail.com");
		message.setText(content.getText());
		message.setSubject(content.getSubject());
		
		mailSender.send(message);
		
		return content;

	}

}
