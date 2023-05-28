package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.AddressDto;
import com.kaly7dev.digitalinvoicing.core_api.dtos.CustomerDto;
import com.kaly7dev.digitalinvoicing.core_api.mappers.AddressMapper;
import com.kaly7dev.digitalinvoicing.core_api.mappers.CustomerMapper;
import com.kaly7dev.digitalinvoicing.entities.Address;
import com.kaly7dev.digitalinvoicing.entities.Customer;
import com.kaly7dev.digitalinvoicing.repositories.AddressRepo;
import com.kaly7dev.digitalinvoicing.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final AddressRepo addressRepo;
    private AddressMapper addressMapper;
    private CustomerMapper customerMapper;
    @Override
    @Transactional
    public void createCustomer(CustomerDto customerDto, AddressDto addressDto) {
        Address addSaved= addressRepo.save(addressMapper.mapToEntity(addressDto));
        Customer custTosave= customerMapper.mapToEntity(customerDto);
        custTosave.setAddress(addSaved);
        customerRepo.save(custTosave);
        log.info("Customer create successfully");
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        return null;
    }

    @Override
    public void deleteCustomer(Long custId) {

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return null;
    }
}
