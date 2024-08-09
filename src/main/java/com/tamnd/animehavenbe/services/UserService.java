package com.tamnd.animehavenbe.services;

import com.tamnd.animehavenbe.domain.dto.UserDTO;
import com.tamnd.animehavenbe.domain.entities.User;
import com.tamnd.animehavenbe.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Page<UserDTO> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        log.debug("Request to getUsers pageable: {}", pageable);
        return userRepository.findAll(pageable).map(UserDTO::new);
    }

    public UserDTO getUser(Long id) {
        log.debug("Request to getUser: {}", id);
        return userRepository.findById(id).map(UserDTO::new).orElse(null);
    }

    public UserDTO createUser(UserDTO userDTO) {
        log.debug("Request to create User: {}", userDTO);
        User user = new User();
//        userRepository.findByEmail(userDTO.getEmail())
//                .ifPresent(e -> {
//                    throw new RuntimeException("Email already exists");
//                });

        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(userDTO.getGender());
        user.setAddress(userDTO.getAddress());
        user.setAvatar(userDTO.getAvatar());
        user.setRoleId(userDTO.getRoleId());

        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.debug("Request to update User: {}", userDTO);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.debug("User not found");
            return null;
        }
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(userDTO.getGender());
        user.setAddress(userDTO.getAddress());
        user.setAvatar(userDTO.getAvatar());
        user.setRoleId(userDTO.getRoleId());
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public void deleteUser(Long id) {
        log.debug("Request to delete User: {}", id);
        userRepository.deleteById(id);
    }
}
