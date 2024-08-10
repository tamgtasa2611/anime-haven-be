package com.tamnd.animehavenbe.repositories;

import com.tamnd.animehavenbe.domain.dto.BrandDTO;
import com.tamnd.animehavenbe.domain.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<BrandDTO> findByName(String name);
}
