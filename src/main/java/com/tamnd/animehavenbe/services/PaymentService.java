package com.tamnd.animehavenbe.services;
import com.tamnd.animehavenbe.domain.dto.BrandDTO;
import com.tamnd.animehavenbe.domain.dto.PaymentDTO;
import com.tamnd.animehavenbe.domain.entities.Brand;
import com.tamnd.animehavenbe.domain.entities.Payment;
import com.tamnd.animehavenbe.repositories.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository PaymentRepository;


    public PaymentService(com.tamnd.animehavenbe.repositories.PaymentRepository paymentRepository) {
        PaymentRepository = paymentRepository;
    }
    
    public Page<PaymentDTO> getPayments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        log.debug("Request to getBrands pageable: {}", pageable);
        return PaymentRepository.findAll(pageable).map(PaymentDTO::new);
    }

    public PaymentDTO getPayment(Long id) {
        log.debug("Request to getBrand: {}", id);
        return PaymentRepository.findById(id).map(PaymentDTO::new).orElse(null);
    }

    public PaymentDTO createPayment(PaymentDTO PaymentDTO) {
        log.debug("Request to create Brand: {}", PaymentDTO);
        Payment Payment = new Payment();
        Payment.setName(PaymentDTO.getName());
        Payment = PaymentRepository.save(Payment);
        return new PaymentDTO(Payment);
    }
    
    public PaymentDTO updateBrand(Long id, PaymentDTO PaymentDTO) {
        log.debug("Request to update Brand: {}", PaymentDTO);
        Payment Payment = PaymentRepository.findById(id).orElse(null);
        if (Payment == null) {
            log.debug("Brand not found");
            return null;
        }

        Payment.setName(PaymentDTO.getName());

        Payment = PaymentRepository.save(Payment);
        return new PaymentDTO(Payment);
    }

    public void deleteBrand(Long id) {
        log.debug("Request to delete Brand: {}", id);
        PaymentRepository.deleteById(id);
    }
    
}