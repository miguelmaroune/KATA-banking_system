package com.springBoot.bankingSystem.service;

import com.springBoot.bankingSystem.entity.BankingUsers;
import com.springBoot.bankingSystem.entity.UserRole;
import com.springBoot.bankingSystem.repository.RolesRepository;
import com.springBoot.bankingSystem.repository.UserRepository;
import com.springBoot.bankingSystem.security.JwtGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RolesRepository userRole;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RolesRepository userRoleRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userRole = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    public String login(String username , String password){
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username,password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtGenerator.generateToken(authentication);
    }

    public void register(String username , String password){
        if(userRepository.existsByUsername(username)){
            throw new RuntimeException("Username is not available!!!");
        }
        BankingUsers user = BankingUsers
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        user.setRoles(List.of(UserRole.builder().name("USER").build()));
        userRepository.save(user);
    }
}
