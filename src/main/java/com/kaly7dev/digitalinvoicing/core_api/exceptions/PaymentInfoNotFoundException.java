package com.kaly7dev.digitalinvoicing.core_api.exceptions;

public class PaymentInfoNotFoundException extends RuntimeException{
    public PaymentInfoNotFoundException(String exMessage){
        super(exMessage);
    }
}
