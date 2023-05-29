package com.kaly7dev.digitalinvoicing.core_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {
    private String invId;
    private String invNumber;
    private CustomerDto customerDto;
    private List<InvoiceItemDto> itemDtoLists;
    private AddressDto billingAddress;
    private PaymentInfoDto paymentInfo;
    private Double totalAmount;
}
