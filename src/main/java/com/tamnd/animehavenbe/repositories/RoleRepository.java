package com.tamnd.animehavenbe.repositories;

import com.tamnd.animehavenbe.domain.dto.RoleDTO;
import com.tamnd.animehavenbe.domain.dto.UserDTO;
import com.tamnd.animehavenbe.domain.entities.Role;
import com.tamnd.animehavenbe.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<RoleDTO> findByName(String name);
}
