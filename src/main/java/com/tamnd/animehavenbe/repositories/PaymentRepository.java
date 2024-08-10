package com.tamnd.animehavenbe.repositories;

import com.tamnd.animehavenbe.domain.dto.PaymentDTO;
import com.tamnd.animehavenbe.domain.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<PaymentDTO> findByName(String name);
}
