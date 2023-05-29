package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.CustomerDto;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    void createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long custId, CustomerDto customerDto);
    void deleteCustomer(Long custId);
    List<CustomerDto> getAllCustomers();
    Map<String, Object> paginateCustomers(String name, int page, int size);
}
