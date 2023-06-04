package com.bulelthell.game.bullethellbackend.controllers.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulelthell.game.bullethellbackend.config.jwt.JwtUtils;
import com.bulelthell.game.bullethellbackend.payload.request.LoginRequest;
import com.bulelthell.game.bullethellbackend.payload.response.JwtResponse;
import com.bulelthell.game.bullethellbackend.payload.response.MessageResponse;
import com.bulelthell.game.bullethellbackend.payload.response.SignupRequest;
import com.bulelthell.game.bullethellbackend.persistence.ERole;
import com.bulelthell.game.bullethellbackend.persistence.Role;
import com.bulelthell.game.bullethellbackend.persistence.User;
import com.bulelthell.game.bullethellbackend.repository.RoleRepository;
import com.bulelthell.game.bullethellbackend.repository.UserRepository;
import com.bulelthell.game.bullethellbackend.service.impl.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	/** Servicio de gestión de aplicaciones */
	@Autowired
	AuthenticationManager authenticationManager;
	
	/** Repositorio de usuarios */
	@Autowired
	UserRepository userRepository;
	
	/** Repositorio de roles */
	@Autowired
	RoleRepository roleRepository;
	
	/** Codificador de passwords */
	@Autowired
	PasswordEncoder encoder;
	
	/** Utilidades Jwt */
	@Autowired
	JwtUtils jwtUtils;

	/**
	 * 
	 * Gestiona el servicio de autentificación de usuarios a la hora de intentar
	 * loggearse en la aplicación
	 * 
	 * @param loginRequest
	 * 
	 * @return ResponseEntity
	 * 
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	/**
	 * 
	 * Gestiona la creación de usuarios en la BDD.
	 * 
	 * @param signUpRequest
	 * 
	 * @return ResponseEntity
	 * 
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username ya existente!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email ya existente!"));
		}
// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role no encontrado."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role no encontrado."));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role no encontrado."));
					roles.add(modRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role no encontrado."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente!"));
	}
}
