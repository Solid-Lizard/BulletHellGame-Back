package com.bulelthell.game.bullethellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulelthell.game.bullethellbackend.rest.EmailRequestRest;
import com.bulelthell.game.bullethellbackend.service.EmailSenderService;

@RestController
@RequestMapping("/email")
public class EmailController {
	@Autowired
	private EmailSenderService sendService;
	
	@PostMapping
	public void sendMail(@RequestBody EmailRequestRest emailContent) {
		sendService.sendEmail(emailContent.getReceiver(),emailContent.getSubject(), emailContent.getText());
	}
	
	

}
