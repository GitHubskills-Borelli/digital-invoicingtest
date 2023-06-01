package com.kaly7dev.digitalinvoicing.services;

import com.kaly7dev.digitalinvoicing.core_api.dtos.AddressDto;

import java.util.List;

public interface AddressService {
     void deleteAddress(Long addId);

    void createAddress(AddressDto addressDto);

    AddressDto updateAddress(Long addId, AddressDto addressDto);
    List<AddressDto> addressList();

}
