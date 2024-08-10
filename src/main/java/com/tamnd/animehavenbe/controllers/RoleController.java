package com.tamnd.animehavenbe.controllers;

import com.tamnd.animehavenbe.domain.dto.RoleDTO;
import com.tamnd.animehavenbe.services.RoleService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
    private final Logger log = LoggerFactory.getLogger(RoleDTO.class);
    private final RoleService roleService;
    private final HttpHeaders headers = new HttpHeaders();


    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getRoles(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "20") int size,
                                                  @RequestParam(value = "sort", defaultValue = "id") String sort) {
        log.debug("REST request to get roles");
        Page<RoleDTO> pageable = roleService.getRoles(page, size);
        return ResponseEntity.ok().headers(headers).body(pageable.getContent());
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable Long id) {
        log.debug("REST request to get Role : {}", id);
        return ResponseEntity.ok().headers(headers).body(roleService.getRole(id));
    }

    @PostMapping("/roles")
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) throws URISyntaxException {
        log.debug("REST request to save role : {}", roleDTO);
        RoleDTO result = roleService.createRole(roleDTO);
        return ResponseEntity.created(new URI("api/roles/" + result.getId())).headers(headers).body(result);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDTO roleDTO) throws BadRequestException {
        log.debug("REST request to update role : {}", roleDTO);
        if(!roleDTO.getId().equals(id)) {
            throw new BadRequestException("Invalid ID");
        }
        RoleDTO result = roleService.updateRole(id, roleDTO);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        log.debug("REST request to delete Role : {}", id);
        roleService.deleteRole(id);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
