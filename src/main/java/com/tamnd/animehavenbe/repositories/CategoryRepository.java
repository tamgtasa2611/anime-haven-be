package com.tamnd.animehavenbe.repositories;

import com.tamnd.animehavenbe.domain.dto.CategoryDTO;
import com.tamnd.animehavenbe.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<CategoryDTO> findByName(String name);
}
