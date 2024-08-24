package com.springBoot.bankingSystem.repository;

import com.springBoot.bankingSystem.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<UserRole,Long> {
    Optional<UserRole> findByName(String name);
}
