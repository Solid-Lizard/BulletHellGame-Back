package com.bulelthell.game.bullethellbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bulelthell.game.bullethellbackend.payload.request.EmailRequestRest;
import com.bulelthell.game.bullethellbackend.persistence.User;
import com.bulelthell.game.bullethellbackend.service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

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

	@Override
	public EmailRequestRest sendRecoverPasswordMail(String mail, String token) {						
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("bullethellgameserver@gmail.com");
		message.setTo(mail);
		message.setText("Para acceder a la petición de cambio de contraseña, acceda a este enlace: http://localhost:4200/reset-password?token=" + token );
		message.setSubject("Recuperación contraseña");

		mailSender.send(message);
		
		EmailRequestRest emailRest = new EmailRequestRest();		
		emailRest.setText(message.getText());
		emailRest.setSubject(message.getSubject());

		return emailRest;
	}

}
