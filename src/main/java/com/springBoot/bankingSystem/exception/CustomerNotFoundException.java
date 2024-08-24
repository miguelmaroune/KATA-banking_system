package com.springBoot.bankingSystem.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long id){
        super("Customer with id "+id+" does not exists!");
    }
}
