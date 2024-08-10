package com.tamnd.animehavenbe.domain.dto;

import com.tamnd.animehavenbe.domain.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PaymentDTO implements Serializable {
    private Long id;
    private String name;


    public PaymentDTO(Payment payment){
        this.id = payment.getId();
        this.name = payment.getName();
    }

}
