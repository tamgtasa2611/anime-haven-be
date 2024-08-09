package com.tamnd.animehavenbe.domain.dto;

import com.tamnd.animehavenbe.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private LocalDate dateOfBirth;
    private Character gender;
    private String address;
    private String avatar;
    private Long roleId;

    public UserDTO(User user) {
        super();
        this.id=user.getId();
        this.fullName=user.getFullName();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.phone=user.getPhone();
        this.dateOfBirth=user.getDateOfBirth();
        this.gender=user.getGender();
        this.address=user.getAddress();
        this.avatar=user.getAvatar();
        this.roleId=user.getRoleId();
    }
}
