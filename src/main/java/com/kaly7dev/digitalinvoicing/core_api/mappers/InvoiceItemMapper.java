package com.kaly7dev.digitalinvoicing.core_api.mappers;

import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceItemDto;
import com.kaly7dev.digitalinvoicing.entities.InvoiceItem;
import org.springframework.stereotype.Component;

@Component
public class InvoiceItemMapper {
    public InvoiceItem mapToEntity(InvoiceItemDto invoiceItemDto){
        return InvoiceItem.builder()
                .name(invoiceItemDto.getName())
                .price(invoiceItemDto.getPrice())
                .quantity(invoiceItemDto.getQuantity())
                .total(invoiceItemDto.getTotal())
                .build();
    }
    public InvoiceItemDto mapToDto(InvoiceItem invoiceItem) {
        return InvoiceItemDto.builder()
                .id(invoiceItem.getId())
                .name(invoiceItem.getName())
                .price(invoiceItem.getPrice())
                .quantity(invoiceItem.getQuantity())
                .total(invoiceItem.getTotal())
                .build();
    }
}
