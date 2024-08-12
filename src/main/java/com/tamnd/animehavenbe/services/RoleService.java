package com.tamnd.animehavenbe.services;

import com.tamnd.animehavenbe.domain.dto.RoleDTO;
import com.tamnd.animehavenbe.domain.entities.Role;
import com.tamnd.animehavenbe.repositories.RoleRepository;
import com.tamnd.animehavenbe.repositories.RoleRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class RoleService {
    private final Logger log = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Page<RoleDTO> getRoles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        log.debug("Request to getRoles pageable: {}", pageable);
        return roleRepository.findAll(pageable).map(RoleDTO::new);
    }

    public RoleDTO getRole(Long id) {
        log.debug("Request to getRole: {}", id);
        return roleRepository.findById(id).map(RoleDTO::new).orElse(null);
    }

    public RoleDTO createRole(RoleDTO roleDTO) {
        log.debug("Request to create Role: {}", roleDTO);
        Role role = new Role();
//        roleRepository.findByEmail(roleDTO.getEmail())
//                .ifPresent(e -> {
//                    throw new RuntimeException("Email already exists");
//                });

        role.setName(roleDTO.getName());

        role = roleRepository.save(role);
        return new RoleDTO(role);
    }

    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        log.debug("Request to update Role: {}", roleDTO);
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            log.debug("Role not found");
            return null;
        }
        role.setName(roleDTO.getName());

        role = roleRepository.save(role);
        return new RoleDTO(role);
    }

    public void deleteRole(Long id) {
        log.debug("Request to delete Role: {}", id);
        roleRepository.deleteById(id);
    }
}
