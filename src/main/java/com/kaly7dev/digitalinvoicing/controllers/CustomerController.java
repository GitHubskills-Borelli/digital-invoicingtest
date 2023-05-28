package com.kaly7dev.digitalinvoicing.controllers;

import com.kaly7dev.digitalinvoicing.core_api.dtos.CustomerDto;
import com.kaly7dev.digitalinvoicing.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customerDto){
        customerService.createCustomer(customerDto);
        return new ResponseEntity<>(CREATED);
    }
    @GetMapping("/lists")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return status(HttpStatus.OK).body(customerService.getAllCustomers());
    }
}
