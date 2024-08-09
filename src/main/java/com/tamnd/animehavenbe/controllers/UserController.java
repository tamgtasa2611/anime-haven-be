package com.tamnd.animehavenbe.controllers;

import com.tamnd.animehavenbe.domain.dto.UserDTO;
import com.tamnd.animehavenbe.services.UserService;
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
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserDTO.class);
    private final UserService userService;
    private final HttpHeaders headers = new HttpHeaders();


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size,
                                                  @RequestParam(value = "sort", defaultValue = "id") String sort) {
        log.debug("REST request to get users");
        Page<UserDTO> pageable = userService.getUsers(page, size);
        return ResponseEntity.ok().headers(headers).body(pageable.getContent());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        return ResponseEntity.ok().headers(headers).body(userService.getUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save user : {}", userDTO);
        UserDTO result = userService.createUser(userDTO);
        return ResponseEntity.created(new URI("api/users/" + result.getId())).headers(headers).body(result);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) throws BadRequestException {
        log.debug("REST request to update user : {}", userDTO);
        if(!userDTO.getId().equals(id)) {
            throw new BadRequestException("Invalid ID");
        }
        UserDTO result = userService.updateUser(id, userDTO);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
