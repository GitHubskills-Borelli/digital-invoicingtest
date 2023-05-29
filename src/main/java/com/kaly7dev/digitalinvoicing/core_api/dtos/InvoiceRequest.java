package com.kaly7dev.digitalinvoicing.core_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequest {
    private String invId;
    private String invNumber;
    private List<InvoiceItemDto> invoiceItemDtoList;
    private Long custId;
    private Long payId;
    private Double totalAmount;
}