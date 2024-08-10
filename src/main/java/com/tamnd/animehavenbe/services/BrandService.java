package com.tamnd.animehavenbe.services;

import com.tamnd.animehavenbe.domain.dto.BrandDTO;
import com.tamnd.animehavenbe.domain.entities.Brand;
import com.tamnd.animehavenbe.repositories.BrandRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BrandService {
    private final Logger log = LoggerFactory.getLogger(BrandService.class);

    private final BrandRepository BrandRepository;

    public BrandService(BrandRepository BrandRepository) {
        this.BrandRepository = BrandRepository;
    }


    public Page<BrandDTO> getBrands(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        log.debug("Request to getBrands pageable: {}", pageable);
        return BrandRepository.findAll(pageable).map(BrandDTO::new);
    }

    public BrandDTO getBrand(Long id) {
        log.debug("Request to getBrand: {}", id);
        return BrandRepository.findById(id).map(BrandDTO::new).orElse(null);
    }

    public BrandDTO createBrand(BrandDTO BrandDTO) {
        log.debug("Request to create Brand: {}", BrandDTO);
        Brand Brand = new Brand();

        Brand.setDescription(BrandDTO.getDescription());
        Brand.setName(BrandDTO.getName());


        Brand = BrandRepository.save(Brand);
        return new BrandDTO(Brand);
    }

    public BrandDTO updateBrand(Long id, BrandDTO BrandDTO) {
        log.debug("Request to update Brand: {}", BrandDTO);
        Brand Brand = BrandRepository.findById(id).orElse(null);
        if (Brand == null) {
            log.debug("Brand not found");
            return null;
        }

        Brand.setDescription(BrandDTO.getDescription());
        Brand.setName(BrandDTO.getName());

        Brand = BrandRepository.save(Brand);
        return new BrandDTO(Brand);
    }

    public void deleteBrand(Long id) {
        log.debug("Request to delete Brand: {}", id);
        BrandRepository.deleteById(id);
    }
}
