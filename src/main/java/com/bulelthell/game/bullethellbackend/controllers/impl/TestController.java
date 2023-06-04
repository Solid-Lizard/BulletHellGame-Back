package com.bulelthell.game.bullethellbackend.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulelthell.game.bullethellbackend.persistence.User;
import com.bulelthell.game.bullethellbackend.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	/** Repositorio de gestión de usuarios */
	@Autowired
	UserRepository repo;
	
	/**
	 * 
	 * Crea un usuario pasado por parámetros
	 * 
	 * @param u
	 * 
	 * @return Usuario
	 */
	@PostMapping
	public User crearUsuario(@RequestBody User u) {
		return repo.save(u);
	}
	
	/**
	 * 
	 * Devuelve todos los usuarios
	 * 
	 * @return Todos los usuarios
	 * 
	 */
	@GetMapping
	public List<User> buscarTodos() {
		return repo.findAll();
	}
	
	/**
	 * 
	 * Devuelve un usuario en base a su id
	 * 
	 * @param id
	 * 
	 * @return Usuario
	 */
	@GetMapping("/{id}")
	public User buscarPorId(@PathVariable Long id) {
		return repo.findById(id).orElse(null);
	}
	
	/**
	 * 
	 * Elimina un usario en base a su id
	 * 
	 * @param id
	 * 
	 */
	@DeleteMapping("/{id}")
	public void eliminarPorId(@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	/**
	 * 
	 * Muestra el contenido público
	 * 
	 * @return String
	 * 
	 */
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	/**
	 * 
	 * Muestra el contenido de los usuarios registrados como "user", "model", o "admin"
	 * 
	 * @return String
	 * 
	 */
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	/**
	 * 
	 * Muestra el contenido de los usuarios registrados como "moderator"
	 * 
	 * @return String
	 * 
	 */
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	/**
	 * 
	 * Muestra el contenido de los usuarios registrados como "admin"
	 * 
	 * @return String
	 * 
	 */
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}

