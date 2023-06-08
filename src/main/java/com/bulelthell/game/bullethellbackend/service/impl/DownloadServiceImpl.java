package com.bulelthell.game.bullethellbackend.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bulelthell.game.bullethellbackend.service.DownloadService;

@Service
public class DownloadServiceImpl implements DownloadService {

	public ResponseEntity<Resource> downloadGame() throws FileNotFoundException {
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

		return ResponseEntity.ok().headers(headers).contentType(mediaType).body(resource);

	}

}
