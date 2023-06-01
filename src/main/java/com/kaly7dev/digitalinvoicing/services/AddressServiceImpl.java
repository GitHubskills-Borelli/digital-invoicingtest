package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.AddressDto;
import com.kaly7dev.digitalinvoicing.core_api.exceptions.AddressNotFoundException;
import com.kaly7dev.digitalinvoicing.core_api.mappers.AddressMapper;
import com.kaly7dev.digitalinvoicing.entities.Address;
import com.kaly7dev.digitalinvoicing.repositories.AddressRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;
    private final AddressMapper addressMapper;

    @Override
    public void deleteAddress(Long addId) {
        Address addressToDelete= addressRepo.findById(addId)
                .orElseThrow(()->new AddressNotFoundException("Address not found"));
        addressRepo.delete(addressToDelete);
        log.info("Address deleted Successfully !");
    }

    @Override
    @Transactional
    public void createAddress(AddressDto addressDto) {
        Address address= Address.builder()
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .zipCode(addressDto.getZipCode())
                .country(addressDto.getCountry())
                .build();

        addressRepo.save(address);
        log.info("Address created successfully !");
    }

    @Override
    @Transactional
    public AddressDto updateAddress(Long addId, AddressDto addressDto) {
        Address addressToUpdate= addressRepo.findById(addId)
                .orElseThrow(()->new AddressNotFoundException("Address not found"));
        addressDto= getAddressUpdated(addressToUpdate,addressDto);
        log.info("Address updated Successfully ! ");
        return addressDto;
    }

    private AddressDto getAddressUpdated(Address address, AddressDto addressDto) {
        address.setCountry(addressDto.getCountry());
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setZipCode(addressDto.getZipCode());

        addressRepo.save(address);
        return addressMapper.maptoDto(address);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDto> addressList() {
        return addressRepo.findAll()
                .stream()
                .map(
                        addressMapper::maptoDto
                ).collect(Collectors.toList());
    }
}
