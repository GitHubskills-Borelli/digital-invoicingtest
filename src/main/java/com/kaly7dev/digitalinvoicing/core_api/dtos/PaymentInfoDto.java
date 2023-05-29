package com.kaly7dev.digitalinvoicing.core_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInfoDto {
    private Long id;
    private String cardType;
    private String cardNumber;
    private Integer expirationMonth;
    private Integer expirationYear;
    private String cvv;
}
