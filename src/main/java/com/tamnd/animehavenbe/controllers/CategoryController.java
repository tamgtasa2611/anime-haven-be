package com.tamnd.animehavenbe.controllers;

import com.tamnd.animehavenbe.domain.dto.CategoryDTO;
import com.tamnd.animehavenbe.services.CategoryService;
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
public class CategoryController {
    private final Logger log = LoggerFactory.getLogger(CategoryDTO.class);
    private final CategoryService categoryService;
    private final HttpHeaders headers = new HttpHeaders();


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategorys(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "20") int size,
                                                  @RequestParam(value = "sort", defaultValue = "id") String sort) {
        log.debug("REST request to get categories");
        Page<CategoryDTO> pageable = categoryService.getCategorys(page, size);
        return ResponseEntity.ok().headers(headers).body(pageable.getContent());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        return ResponseEntity.ok().headers(headers).body(categoryService.getCategory(id));
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws URISyntaxException {
        log.debug("REST request to save category : {}", categoryDTO);
        CategoryDTO result = categoryService.createCategory(categoryDTO);
        return ResponseEntity.created(new URI("api/categories/" + result.getId())).headers(headers).body(result);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) throws BadRequestException {
        log.debug("REST request to update category : {}", categoryDTO);
        if(!categoryDTO.getId().equals(id)) {
            throw new BadRequestException("Invalid ID");
        }
        CategoryDTO result = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
