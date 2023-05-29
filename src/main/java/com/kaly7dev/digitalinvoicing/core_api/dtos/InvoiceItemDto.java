package com.kaly7dev.digitalinvoicing.core_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceItemDto {
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private double total;
}
