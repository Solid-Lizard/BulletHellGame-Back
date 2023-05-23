package com.bulelthell.game.bullethellbackend.service;

import com.bulelthell.game.bullethellbackend.rest.EmailRequestRest;

public interface EmailSenderService {
	public EmailRequestRest sendEmail(EmailRequestRest content);

}
