package com.bulelthell.game.bullethellbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.bulelthell.game.bullethellbackend.payload.request.EmailRequestRest;
import com.bulelthell.game.bullethellbackend.payload.response.BulletHellGameResponse;

public interface EmailController {
	public ResponseEntity<BulletHellGameResponse<EmailRequestRest>> sendMail(@RequestBody EmailRequestRest emailContent);

}
