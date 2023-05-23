package com.bulelthell.game.bullethellbackend.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulelthell.game.bullethellbackend.controllers.EmailController;
import com.bulelthell.game.bullethellbackend.response.BulletHellGameResponse;
import com.bulelthell.game.bullethellbackend.rest.EmailRequestRest;
import com.bulelthell.game.bullethellbackend.service.impl.EmailSenderServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/email")
public class EmailControllerImpl implements EmailController {
	@Autowired
	private EmailSenderServiceImpl sendService;
	
	@PostMapping
	public ResponseEntity<BulletHellGameResponse<EmailRequestRest>> sendMail(@RequestBody EmailRequestRest emailContent) {
				
		BulletHellGameResponse response = BulletHellGameResponse.builder()
				.message("Email succesfully sent")
				.status(HttpStatus.OK)
				.data(sendService.sendEmail(emailContent))								
				.build();

		
		return ResponseEntity.ok(response);
	}
	
	

}
