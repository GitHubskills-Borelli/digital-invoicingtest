package com.kaly7dev.digitalinvoicing.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardType;
    private String cardNumber;
    private Integer expirationMonth;
    private Integer expirationYear;
    private String cvv;
}
