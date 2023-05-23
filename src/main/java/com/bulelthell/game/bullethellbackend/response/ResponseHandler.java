package com.bulelthell.game.bullethellbackend.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		
        BulletHellGameResponse response = BulletHellGameResponse.builder()
        		.data(responseObj)
        		.status(status)
        		.message(message)
        		.build();
   
            return new ResponseEntity<>(response,status);
    }

}
