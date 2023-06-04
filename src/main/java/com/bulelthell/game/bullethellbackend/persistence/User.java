package com.bulelthell.game.bullethellbackend.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Mapeo de la tabla de usuarios
 * 
 * @author Santiago López 
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {	
	// ATRIBUTOS //
	/**
	 * 
	 * Id de la entidad
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 
	 * Nombre de usuario
	 * 
	 */
	@NotBlank
	@Size(max = 20)
	private String username;
	
	/**
	 * 
	 * Email del usuario
	 * 
	 */
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	/**
	 * 
	 * Password del usuario
	 * 
	 */
	@NotBlank
	@Size(max = 120)
	private String password;
	
	/**
	 * 
	 * Roles del usuario
	 * 
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
