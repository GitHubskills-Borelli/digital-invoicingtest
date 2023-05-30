package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceRequest;
import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceResponse;

import java.util.List;

public interface InvoiceService {
    void createInvoice(InvoiceRequest invoiceRequest);
    void deleteInvoice(String invId);
    void updateInvoice(String invId, InvoiceRequest invoiceDto);
    List<InvoiceResponse> listInvoice();


}
