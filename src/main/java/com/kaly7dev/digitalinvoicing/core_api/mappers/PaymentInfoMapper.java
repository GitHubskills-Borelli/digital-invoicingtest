package com.kaly7dev.digitalinvoicing.core_api.mappers;

import com.kaly7dev.digitalinvoicing.core_api.dtos.PaymentInfoDto;
import com.kaly7dev.digitalinvoicing.entities.PaymentInfo;
import org.springframework.stereotype.Component;

@Component
public class PaymentInfoMapper {
    public PaymentInfo mapToEntity(PaymentInfoDto paymentInfoDto){
        return PaymentInfo.builder()
                .cardType(paymentInfoDto.getCardType())
                .cardNumber(paymentInfoDto.getCardNumber())
                .expirationMonth(paymentInfoDto.getExpirationMonth())
                .expirationYear(paymentInfoDto.getExpirationYear())
                .cvv(paymentInfoDto.getCvv())
                .build();
    }

    public PaymentInfoDto mapToDto(PaymentInfo paymentInfo){
        return PaymentInfoDto.builder()
                .id(paymentInfo.getId())
                .cardType(paymentInfo.getCardType())
                .cardNumber(paymentInfo.getCardNumber())
                .expirationMonth(paymentInfo.getExpirationMonth())
                .expirationYear(paymentInfo.getExpirationYear())
                .cvv(paymentInfo.getCvv())
                .build();
    }
}
