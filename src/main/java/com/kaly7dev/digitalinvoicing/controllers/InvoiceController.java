package com.kaly7dev.digitalinvoicing.controllers;

import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceRequest;
import com.kaly7dev.digitalinvoicing.core_api.dtos.InvoiceResponse;
import com.kaly7dev.digitalinvoicing.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    @PostMapping("/create")
    public ResponseEntity<String> createInvoice(@RequestBody InvoiceRequest invoiceRequest){
        invoiceService.createInvoice(invoiceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<InvoiceResponse>> getInvoiceList(){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.listInvoice());
    }
    @PutMapping("/update/{invId}")
    public ResponseEntity<String> updateInvoice(@PathVariable String invId,
                                                @RequestBody InvoiceRequest invoiceRequest){
        invoiceService.updateInvoice(invId,invoiceRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/{invId}")
    public ResponseEntity<String> deleteInvoice(@PathVariable String invId){
        invoiceService.deleteInvoice(invId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
