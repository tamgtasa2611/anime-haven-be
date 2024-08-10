package  com.tamnd.animehavenbe.controllers;

import com.tamnd.animehavenbe.domain.dto.PaymentDTO;
import com.tamnd.animehavenbe.domain.dto.PaymentDTO;

import com.tamnd.animehavenbe.services.PaymentService;
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
public class PaymentController{
    private final Logger log = LoggerFactory.getLogger(PaymentDTO.class);
    private final PaymentService paymentService;
    private final HttpHeaders headers = new HttpHeaders();

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDTO>> getPayments(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "20") int size,
                                                    @RequestParam(value = "sort", defaultValue = "id") String sort) {
        log.debug("REST request to get payments");
        Page<PaymentDTO> pageable = paymentService.getPayments(page, size);
        return ResponseEntity.ok().headers(headers).body(pageable.getContent());
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        log.debug("REST request to get payment : {}", id);
        return ResponseEntity.ok().headers(headers).body(paymentService.getPayment(id));
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO PaymentDTO) throws URISyntaxException {
        log.debug("REST request to save payment : {}", PaymentDTO);
        PaymentDTO result = paymentService.createPayment(PaymentDTO);
        return ResponseEntity.created(new URI("api/brands/" + result.getId())).headers(headers).body(result);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO PaymentDTO) throws BadRequestException {
        log.debug("REST request to update payment : {}", PaymentDTO);
        if(!PaymentDTO.getId().equals(id)) {
            throw new BadRequestException("Invalid ID");
        }
        PaymentDTO result = paymentService.updateBrand(id, PaymentDTO);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        log.debug("REST request to delete payment : {}", id);
        paymentService.deleteBrand(id);
        return ResponseEntity.noContent().headers(headers).build();
    }
}