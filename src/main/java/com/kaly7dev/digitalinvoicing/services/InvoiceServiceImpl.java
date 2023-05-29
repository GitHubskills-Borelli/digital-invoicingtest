package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceItemDto;
import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceRequest;
import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceResponse;
import com.kaly7dev.digitalinvoicing.core_api.exceptions.CustomerNotFoundException;
import com.kaly7dev.digitalinvoicing.core_api.exceptions.PaymentInfoNotFoundException;
import com.kaly7dev.digitalinvoicing.core_api.mappers.AddressMapper;
import com.kaly7dev.digitalinvoicing.core_api.mappers.InvoiceItemMapper;
import com.kaly7dev.digitalinvoicing.core_api.mappers.InvoiceMapper;
import com.kaly7dev.digitalinvoicing.entities.*;
import com.kaly7dev.digitalinvoicing.repositories.CustomerRepo;
import com.kaly7dev.digitalinvoicing.repositories.InvoiceItemRepo;
import com.kaly7dev.digitalinvoicing.repositories.InvoiceRepo;
import com.kaly7dev.digitalinvoicing.repositories.PaymentInfoRepo;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Service
@Slf4j
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepo invoiceRepo;
    private final InvoiceItemRepo invoiceItemRepo;
    private final PaymentInfoRepo paymentInfoRepo;
    private final CustomerRepo customerRepo;
    private final InvoiceItemMapper invoiceItemMapper;
    private final AddressMapper addressMapper;
    private final InvoiceMapper invoiceMapper;
    @Override
    @Transactional
    public void createInvoice(InvoiceRequest invoiceRequest) {
        Customer customerInvoice= customerRepo.findById(invoiceRequest.getCustId())
                .orElseThrow(()-> new CustomerNotFoundException("Customer not exist !"));
        PaymentInfo paymentInfoInvoice= paymentInfoRepo.findById(invoiceRequest.getPayId())
                .orElseThrow(()-> new PaymentInfoNotFoundException(" Info payment Not exist ! "));

        Invoice invoice= Invoice.builder()
                .invId(UUID.randomUUID().toString())
                .invNumber(invoiceRequest.getInvNumber())
                .customer(customerInvoice)
                .items(getInvoiceItemList(invoiceRequest.getInvoiceItemDtoList()))
                .billingAddress(customerInvoice.getAddress())
                .paymentInfo(paymentInfoInvoice)
                .totalAmount(invoiceRequest.getTotalAmount())
                .build();

        invoiceRepo.save(invoice);
        log.info(" Invoice created successfully");
    }
    private List<InvoiceItem> getInvoiceItemList(List<InvoiceItemDto> invoiceItemDtoList){
        List<InvoiceItem> invoiceItemList=new ArrayList<>();
        InvoiceItem invoiceItem;
        for(InvoiceItemDto invoiceItemDto:invoiceItemDtoList){
            invoiceItem= invoiceItemMapper.mapToEntity(invoiceItemDto);
            invoiceItemList.add(invoiceItemRepo.save(invoiceItem));
        }
        return invoiceItemList;
    }

    @Override
    public void deleteInvoice(String invId) {

    }
    @Override
    public InvoiceResponse updateInvoice(String invId, InvoiceRequest invoiceRequest) {
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse> listInvoice() {
        return invoiceRepo.findAll()
                .stream()
                .map(
                        invoiceMapper::mapToDto
                )
                .toList();
    }

}
