package com.bulelthell.game.bullethellbackend.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController {
	
	private Resource game = new ClassPathResource("BulletHell.zip");
	
	@GetMapping
	public ResponseEntity<Resource> downloadGame() throws IOException {
        // Ruta al archivo ZIP que deseas descargar
        String filePath = "./BulletHell.zip";
        
        // Crea un objeto FileInputStream para leer el archivo
        InputStream inputStream = new FileInputStream(filePath);
        
        // Crea un objeto InputStreamResource para envolver el InputStream
        InputStreamResource resource = new InputStreamResource(inputStream);
        
        // Configura las cabeceras de la respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=BulletHell.zip");
        
        // Establece el tipo de contenido del archivo ZIP
        MediaType mediaType = MediaType.parseMediaType("application/zip");
        
        // Crea una respuesta HTTP con el archivo ZIP y las cabeceras configuradas
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(mediaType)
                .body(resource);
    }
}
