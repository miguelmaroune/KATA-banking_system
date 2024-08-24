package com.springBoot.bankingSystem.repository;

import com.springBoot.bankingSystem.entity.BankingUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BankingUsers,Long> {
    Optional<BankingUsers> findByUsername(String username);
    Boolean existsByUsername(String username);
}
