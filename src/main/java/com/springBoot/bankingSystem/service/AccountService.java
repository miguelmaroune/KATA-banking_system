package com.springBoot.bankingSystem.service;

import com.springBoot.bankingSystem.entity.Account;
import com.springBoot.bankingSystem.entity.Customer;
import com.springBoot.bankingSystem.exception.AccountNotFoundException;
import com.springBoot.bankingSystem.exception.CustomerNotFoundException;
import com.springBoot.bankingSystem.repository.AccountRepository;
import com.springBoot.bankingSystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;


    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Long CustomerId , String currency){
        Customer customer = this.customerRepository.findById(CustomerId).orElseThrow(()-> new CustomerNotFoundException(CustomerId));
        Account newAccount = Account.builder()
                .customer(customer)
                .balance(BigDecimal.ZERO)
                .currency(currency)
                .build();
        return this.accountRepository.save(newAccount);
    }

    public Account findAccountByCustomer(Long customerId){
        return this.accountRepository.findByCustomerId(customerId).orElseThrow(()-> new AccountNotFoundException(customerId));
    }
}
