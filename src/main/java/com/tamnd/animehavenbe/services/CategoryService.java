package com.tamnd.animehavenbe.services;

import com.tamnd.animehavenbe.domain.dto.CategoryDTO;
import com.tamnd.animehavenbe.domain.entities.Category;
import com.tamnd.animehavenbe.repositories.CategoryRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Page<CategoryDTO> getCategorys(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        log.debug("Request to getCategorys pageable: {}", pageable);
        return categoryRepository.findAll(pageable).map(CategoryDTO::new);
    }

    public CategoryDTO getCategory(Long id) {
        log.debug("Request to getCategory: {}", id);
        return categoryRepository.findById(id).map(CategoryDTO::new).orElse(null);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        log.debug("Request to create Category: {}", categoryDTO);
        Category category = new Category();
//        categoryRepository.findByEmail(categoryDTO.getEmail())
//                .ifPresent(e -> {
//                    throw new RuntimeException("Email already exists");
//                });

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        category = categoryRepository.save(category);
        return new CategoryDTO(category);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        log.debug("Request to update Category: {}", categoryDTO);
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            log.debug("Category not found");
            return null;
        }
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        category = categoryRepository.save(category);
        return new CategoryDTO(category);
    }

    public void deleteCategory(Long id) {
        log.debug("Request to delete Category: {}", id);
        categoryRepository.deleteById(id);
    }
}
