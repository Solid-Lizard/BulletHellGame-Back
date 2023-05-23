package com.bulelthell.game.bullethellbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.bulelthell.game.bullethellbackend.response.BulletHellGameResponse;
import com.bulelthell.game.bullethellbackend.rest.EmailRequestRest;

public interface EmailController {
	public ResponseEntity<BulletHellGameResponse<EmailRequestRest>> sendMail(@RequestBody EmailRequestRest emailContent);

}
