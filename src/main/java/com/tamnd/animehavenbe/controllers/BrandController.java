package com.tamnd.animehavenbe.controllers;

import com.tamnd.animehavenbe.domain.dto.BrandDTO;
import com.tamnd.animehavenbe.services.BrandService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {

    private final Logger log = LoggerFactory.getLogger(BrandDTO.class);
    private final BrandService brandService;
    private final HttpHeaders headers = new HttpHeaders();


    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> getBrands(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "20") int size,
                                                  @RequestParam(value = "sort", defaultValue = "id") String sort) {
        log.debug("REST request to get brands");
        Page<BrandDTO> pageable = brandService.getBrands(page, size);
        return ResponseEntity.ok().headers(headers).body(pageable.getContent());
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable Long id) {
        log.debug("REST request to get Brand : {}", id);
        return ResponseEntity.ok().headers(headers).body(brandService.getBrand(id));
    }

    @PostMapping("/brands")
    public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody BrandDTO brandDTO) throws URISyntaxException {
        log.debug("REST request to save brand : {}", brandDTO);
        BrandDTO result = brandService.createBrand(brandDTO);
        return ResponseEntity.created(new URI("api/brands/" + result.getId())).headers(headers).body(result);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandDTO brandDTO) throws BadRequestException {
        log.debug("REST request to update brand : {}", brandDTO);
        if(!brandDTO.getId().equals(id)) {
            throw new BadRequestException("Invalid ID");
        }
        BrandDTO result = brandService.updateBrand(id, brandDTO);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        log.debug("REST request to delete Brand : {}", id);
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
