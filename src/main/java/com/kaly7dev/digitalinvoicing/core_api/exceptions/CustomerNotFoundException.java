package com.kaly7dev.digitalinvoicing.core_api.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String exMessage){
        super(exMessage);
    }
}
