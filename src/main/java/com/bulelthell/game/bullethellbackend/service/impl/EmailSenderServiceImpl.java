package com.bulelthell.game.bullethellbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bulelthell.game.bullethellbackend.payload.request.EmailRequestRest;
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
		message.setText("Para acceder a la peticion de cambio de contrasenia, acceda a este enlace: https://solid-lizard.github.io/BulletHellGame/reset-password?token=" + token );
		message.setSubject("Recuperacion contrasenia");

		mailSender.send(message);
		
		EmailRequestRest emailRest = new EmailRequestRest();		
		emailRest.setText(message.getText());
		emailRest.setSubject(message.getSubject());

		return emailRest;
	}

	@Override
	public EmailRequestRest sendUserDeletedMail(String mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("bullethellgameserver@gmail.com");
		message.setTo(mail);
		message.setSubject("Información sobre eliminación de usuario");
		message.setText("Buenas, sentimos informarle que su usuario ha sido eliminado de nuestra base de datos por un administrador, si tiene alguna queja o desea más información no dude contactarnos al siguiente correo: santi.lopar@gmail.com");
		
		mailSender.send(message);
		
		return null;
	}

}
