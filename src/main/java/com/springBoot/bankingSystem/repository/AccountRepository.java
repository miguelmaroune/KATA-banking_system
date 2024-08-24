package com.springBoot.bankingSystem.repository;

import com.springBoot.bankingSystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByCustomerId(Long accountId);
}
