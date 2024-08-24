package com.springBoot.bankingSystem.exception;

public class InsufficientFundsException extends RuntimeException{
private Long id;
    public InsufficientFundsException(Long id){
        super("Account with id "+id+" does not have sufficient funds to perform this transaction!!");
    }
}
