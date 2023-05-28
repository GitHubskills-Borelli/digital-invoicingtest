package com.kaly7dev.digitalinvoicing.core_api.mappers;

import com.kaly7dev.digitalinvoicing.core_api.dtos.CustomerDto;
import com.kaly7dev.digitalinvoicing.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto mapToDto(Customer customer) {
        return CustomerDto.builder()
                .custId(customer.getCustId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                //.address(customer.getAddress())
                .build();
    }

    public Customer mapToEntity(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .address(null)
                .build();
    }
}
