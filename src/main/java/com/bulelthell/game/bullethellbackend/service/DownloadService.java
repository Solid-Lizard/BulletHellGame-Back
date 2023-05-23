package com.bulelthell.game.bullethellbackend.service;

import java.io.FileNotFoundException;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface DownloadService {
	public ResponseEntity<Resource> downloadGame() throws FileNotFoundException;
}
