package com.bulelthell.game.bullethellbackend.controllers;

import org.springframework.http.ResponseEntity;

import com.bulelthell.game.bullethellbackend.payload.request.LoginRequest;
import com.bulelthell.game.bullethellbackend.payload.request.SignupRequest;

public interface AuthControllerI {
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
	
	public ResponseEntity<?> registerUser(SignupRequest signupRequest);
	

}
