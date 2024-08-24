package com.springBoot.bankingSystem.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(Long id){
        super("Account with Customer id "+id+" does not exists!");
    }
}
