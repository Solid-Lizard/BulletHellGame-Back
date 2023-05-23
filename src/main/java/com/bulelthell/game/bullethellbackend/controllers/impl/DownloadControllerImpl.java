package com.bulelthell.game.bullethellbackend.controllers.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulelthell.game.bullethellbackend.controllers.DownloadController;
import com.bulelthell.game.bullethellbackend.service.DownloadService;

@RestController
@RequestMapping("/download")
public class DownloadControllerImpl implements DownloadController {
	@Autowired
	DownloadService downloadService;
	
	
	@GetMapping
	public ResponseEntity<Resource> downloadGame() throws IOException {        
        
        // Crea una respuesta HTTP con el archivo ZIP y las cabeceras configuradas
        return downloadService.downloadGame();
    }
}
