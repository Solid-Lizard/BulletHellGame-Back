package com.bulelthell.game.bullethellbackend.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Gestiona el mapeo de las entidades de los roles de usuario existentes
 * 
 * @see User
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
@Table(name = "roles")
public class Role {

	// ATRIBUTOS //
	/** ID de la entidad */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = null;
	
	/** Tipo de usuario */
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	
}
