package com.bulelthell.game.bullethellbackend.service;

import com.bulelthell.game.bullethellbackend.payload.request.EmailRequestRest;

public interface EmailSenderService {
	public EmailRequestRest sendEmail(EmailRequestRest content);
	
	public EmailRequestRest sendRecoverPasswordMail(String mail, String token);
	
	public EmailRequestRest sendUserDeletedMail (String mail);

}
