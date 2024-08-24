package com.springBoot.bankingSystem.controller;

import com.springBoot.bankingSystem.entity.Customer;
import com.springBoot.bankingSystem.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(this.customerService.createCustomer(customer));
    }
    @GetMapping("{/id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(this.customerService.findCustomerById(id));
    }
}
