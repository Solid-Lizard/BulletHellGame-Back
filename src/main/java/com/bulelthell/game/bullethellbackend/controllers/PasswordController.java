package com.bulelthell.game.bullethellbackend.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bulelthell.game.bullethellbackend.payload.request.EmailRequestRest;
import com.bulelthell.game.bullethellbackend.payload.response.BulletHellGameResponse;
import com.bulelthell.game.bullethellbackend.persistence.User;
import com.bulelthell.game.bullethellbackend.repository.UserRepository;
import com.bulelthell.game.bullethellbackend.service.EmailSenderService;

@RestController
@RequestMapping("/password")
public class PasswordController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	EmailSenderService sendService;
	
	@Autowired
	PasswordEncoder encoder;
	
	UUID token = UUID.randomUUID();
	
	User usuario = new User();
	
	@PostMapping("/recover-request")
	public ResponseEntity<BulletHellGameResponse<EmailRequestRest>> recoverPasswordRequest(@RequestParam String userEmail)
			throws Exception {

		usuario = userRepo.findByEmail(userEmail);		
		
		if (usuario != null) {
			token = UUID.randomUUID();
			
			BulletHellGameResponse response = BulletHellGameResponse.builder()
					.message(userEmail)
					.status(HttpStatus.OK)
					.data(sendService.sendRecoverPasswordMail("santi.lopar@gmail.com", token.toString())).build();
			
			return ResponseEntity.ok(response);
			
		} else {
			throw new Exception("Email not found");
		}
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<BulletHellGameResponse> updatePassword(
			@RequestParam String token,
			@RequestParam String newPassword) throws Exception {
		
		if (this.token.toString().equals(token)) {
			usuario.setPassword(encoder.encode(newPassword));
			userRepo.save(usuario);
			
		} else {
			throw new Exception("No tienes los permisos necesarios");
		}
		
		BulletHellGameResponse response = BulletHellGameResponse.builder()
				.status(HttpStatus.OK)
				.message("Password updated succesfully")
				.data(newPassword)
				.build();
		
		return ResponseEntity.ok(response);
		
	}			 
	
}
