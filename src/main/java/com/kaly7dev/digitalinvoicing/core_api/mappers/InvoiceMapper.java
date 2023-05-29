package com.kaly7dev.digitalinvoicing.core_api.mappers;

import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceItemDto;
import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceResponse;
import com.kaly7dev.digitalinvoicing.entities.Invoice;
import com.kaly7dev.digitalinvoicing.entities.InvoiceItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class InvoiceMapper {
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;
    private final InvoiceItemMapper invoiceItemMapper;
    public InvoiceResponse mapToDto(Invoice invoice){
        return InvoiceResponse.builder()
                .invId(invoice.getInvId())
                .invNumber(invoice.getInvNumber())
                .totalAmount(invoice.getTotalAmount())
                .customerDto(customerMapper.mapToDto(invoice.getCustomer()))
                .billingAddress(addressMapper.maptoDto(invoice.getBillingAddress()))
                //.paymentInfo()
                .itemDtoLists(getInvoiceItemDtoList(invoice.getItems()))
                .build();
    }
    private List<InvoiceItemDto> getInvoiceItemDtoList(List<InvoiceItem> invoiceItemList){
        List<InvoiceItemDto> invoiceItemDtoList=new ArrayList<>();
        InvoiceItemDto invoiceItemDto;
        for(InvoiceItem invoiceItem:invoiceItemList){
            invoiceItemDto= invoiceItemMapper.mapToDto(invoiceItem);
            invoiceItemDtoList.add(invoiceItemDto);
        }
        return invoiceItemDtoList;
    }
}
