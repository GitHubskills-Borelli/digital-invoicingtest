package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.AddressDto;
import com.kaly7dev.digitalinvoicing.core_api.dtos.CustomerDto;
import com.kaly7dev.digitalinvoicing.core_api.exceptions.CustomerNotFoundException;
import com.kaly7dev.digitalinvoicing.core_api.exceptions.AddressNotFoundException;
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
    @Transactional
    public CustomerDto updateCustomer(Long custId, CustomerDto customerDto) {
        Customer custtoUpdate= customerRepo.findById(custId)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found with ID: "+custId.toString()));

        return getUpdatedCustomer(custtoUpdate, customerDto);
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

    /**
     * this fonction make a update process on customer repository
     * @param customer
     * @param customerDto
     * @return customerDto
     */
    private CustomerDto getUpdatedCustomer(Customer customer, CustomerDto customerDto) {
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());

        customer.setAddress(getUpdatedAddress(customer.getAddress(),customerDto.getAddressDto()));

        customerDto.setCustId(customer.getCustId());
        customerDto.getAddressDto().setAddId(customer.getAddress().getAddId());

        customerRepo.save(customer);
        log.info("Customer updated successfully ! ");
        return customerDto;
    }

    private Address getUpdatedAddress(Address address, AddressDto addressDto) {
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setZipCode(addressDto.getZipCode());
        address.setCountry(addressDto.getCountry());

        return addressRepo.save(address);
    }
}
