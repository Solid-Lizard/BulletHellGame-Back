package com.bulelthell.game.bullethellbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bulelthell.game.bullethellbackend.persistence.ERole;
import com.bulelthell.game.bullethellbackend.persistence.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}