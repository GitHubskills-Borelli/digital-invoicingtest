package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.PaymentInfoDto;

import java.util.List;

public interface PaymentInfoService {
    void createPaymentInfo(PaymentInfoDto paymentInfoDto);
    List<PaymentInfoDto> paymentInfoList();

    void updatePaymentInfo(Long payId, PaymentInfoDto paymentInfoDto);

    void deletePaymentInfo(Long payId);
}
