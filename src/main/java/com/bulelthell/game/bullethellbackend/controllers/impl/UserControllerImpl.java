package com.bulelthell.game.bullethellbackend.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulelthell.game.bullethellbackend.persistence.User;
import com.bulelthell.game.bullethellbackend.repository.UserRepository;
import com.bulelthell.game.bullethellbackend.service.EmailSenderService;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserControllerImpl {
	
	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	public EmailSenderService emailSender;
	
	@GetMapping("/allUsers")
	public List<User> getAllUsers() {
		return userRepo.findAll();
		
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		User usuario = userRepo.findById(id).orElse(null);
		
		emailSender.sendUserDeletedMail(usuario.getEmail());
		
		userRepo.deleteById(id);
	}

}
