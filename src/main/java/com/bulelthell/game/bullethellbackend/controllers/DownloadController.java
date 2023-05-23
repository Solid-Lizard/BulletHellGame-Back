package com.bulelthell.game.bullethellbackend.controllers;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface DownloadController {
	public ResponseEntity<Resource> downloadGame() throws IOException;

}
