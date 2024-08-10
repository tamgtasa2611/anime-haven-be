package com.tamnd.animehavenbe.domain.dto;

import com.tamnd.animehavenbe.domain.entities.Brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BrandDTO implements Serializable {
    private Long id;
    private String name;
    private String description;

    public BrandDTO(Brand brand){
        this.id = brand.getId();
        this.name = brand.getName();
        this.description = brand.getDescription();
    }
}
