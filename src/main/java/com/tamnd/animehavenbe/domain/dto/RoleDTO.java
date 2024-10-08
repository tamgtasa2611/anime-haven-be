package com.tamnd.animehavenbe.domain.dto;

import com.tamnd.animehavenbe.domain.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Serializable {
    private Long id;
    private String name;

    public RoleDTO(Role role) {
        super();
        this.id=role.getId();
        this.name=role.getName();
    }
}
