package com.springBoot.bankingSystem.service;

import com.springBoot.bankingSystem.entity.Customer;
import com.springBoot.bankingSystem.exception.CustomerNotFoundException;
import com.springBoot.bankingSystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id ){
        return this.customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
    }
}
