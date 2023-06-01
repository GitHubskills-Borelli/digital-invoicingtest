package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceItemDto;
import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceRequest;
import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceResponse;
import com.kaly7dev.digitalinvoicing.core_api.exceptions.CustomerNotFoundException;
import com.kaly7dev.digitalinvoicing.core_api.exceptions.PaymentInfoNotFoundException;
import com.kaly7dev.digitalinvoicing.core_api.exceptions.InvoiceNotFoundException;
import com.kaly7dev.digitalinvoicing.core_api.mappers.AddressMapper;
import com.kaly7dev.digitalinvoicing.core_api.mappers.InvoiceItemMapper;
import com.kaly7dev.digitalinvoicing.core_api.mappers.InvoiceMapper;
import com.kaly7dev.digitalinvoicing.entities.*;
import com.kaly7dev.digitalinvoicing.repositories.CustomerRepo;
import com.kaly7dev.digitalinvoicing.repositories.InvoiceItemRepo;
import com.kaly7dev.digitalinvoicing.repositories.InvoiceRepo;
import com.kaly7dev.digitalinvoicing.repositories.PaymentInfoRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Integer size= invoiceRepo.findAll().size();

        Invoice invoice= Invoice.builder()
                .invId(UUID.randomUUID().toString())
                .invNumber(getInvoiceNumber(size))
                .customer(getCustomerInDb(invoiceRequest.getCustId()))
                .items(getInvoiceItemList(invoiceRequest.getInvoiceItemDtoList()))
                .billingAddress(getCustomerInDb(invoiceRequest.getCustId()).getAddress())
                .paymentInfo(getPaymentInfoInDb(invoiceRequest.getPayId()))
                .totalAmount(invoiceRequest.getTotalAmount())
                .build();

        invoiceRepo.save(invoice);
        log.info(" Invoice created successfully");
    }


    private PaymentInfo getPaymentInfoInDb(Long payId) {
        return paymentInfoRepo.findById(payId)
                .orElseThrow(()-> new PaymentInfoNotFoundException(" Info payment Not exist ! "));
    }

    private Customer getCustomerInDb(Long custId) {
        return customerRepo.findById(custId)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not exist !"));
    }

    private  String getInvoiceNumber(Integer size) {
        return String.format("inv-"+size);
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
    @Transactional
    public void deleteInvoice(String invId) {
        invoiceRepo.delete(getInvoiceInDB(invId));
    }
    @Override
    @Transactional
    public void updateInvoice(String invId, InvoiceRequest invoiceRequest) {
        Invoice invoiceToUpdate = getInvoiceInDB(invId);

        invoiceToUpdate.setCustomer(getCustomerInDb(invoiceRequest.getCustId()));
        invoiceToUpdate.setPaymentInfo(getPaymentInfoInDb(invoiceRequest.getPayId()));
        invoiceToUpdate.setTotalAmount(invoiceRequest.getTotalAmount());
        invoiceToUpdate.setItems(getInvoiceItemList(invoiceRequest.getInvoiceItemDtoList()));

        invoiceRepo.save(invoiceToUpdate);
        log.info("Invoice updated successfully !");
    }

    private Invoice getInvoiceInDB(String invId) {
        return invoiceRepo.findById(invId)
                .orElseThrow(()->new InvoiceNotFoundException("Invoice not found"));
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
