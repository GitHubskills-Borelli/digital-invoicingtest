package com.kaly7dev.digitalinvoicing.controllers;

import com.kaly7dev.digitalinvoicing.core_api.dtos.AddressDto;
import com.kaly7dev.digitalinvoicing.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @GetMapping("/list")
    public ResponseEntity<List<AddressDto>> addressList(){
        return ResponseEntity.status(OK).body(addressService.addressList());
    }
    @PutMapping("/update/{addId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long addId,
                                                    @RequestBody AddressDto addressDto){
        return ResponseEntity.status(OK).body(addressService.updateAddress(addId, addressDto));
    }
}
