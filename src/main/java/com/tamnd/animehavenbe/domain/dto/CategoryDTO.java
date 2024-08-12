package com.tamnd.animehavenbe.domain.dto;

import com.tamnd.animehavenbe.domain.entities.Category;
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
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;
    private String description;

    public CategoryDTO(Category category) {
        super();
        this.id=category.getId();
        this.name=category.getName();
        this.description=category.getDescription();
    }
}
