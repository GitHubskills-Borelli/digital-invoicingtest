package com.kaly7dev.digitalinvoicing.core_api.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String exMessage){
        super(exMessage);
    }
}
