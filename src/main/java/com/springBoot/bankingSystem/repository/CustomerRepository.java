package com.springBoot.bankingSystem.repository;

import com.springBoot.bankingSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {}
