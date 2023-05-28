package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.CustomerDto;
import com.kaly7dev.digitalinvoicing.core_api.mappers.AddressMapper;
import com.kaly7dev.digitalinvoicing.core_api.mappers.CustomerMapper;
import com.kaly7dev.digitalinvoicing.entities.Address;
import com.kaly7dev.digitalinvoicing.entities.Customer;
import com.kaly7dev.digitalinvoicing.repositories.AddressRepo;
import com.kaly7dev.digitalinvoicing.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public void createCustomer(CustomerDto customerDto) {
        Address addSaved= addressRepo.save(addressMapper.mapToEntity(customerDto.getAddressDto()));
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
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtoList= new ArrayList<>();
        List<Customer> customerList = customerRepo.findAll();
        CustomerDto customerDto;
        for(Customer customer:customerList){
            customerDto= customerMapper.mapToDto(customer);
            customerDto.setAddressDto(addressMapper.maptoDto(customer.getAddress()));
            customerDtoList.add(customerDto);
        }
        log.info("the list of customers successfully displayed");
        return customerDtoList;
    }
}
