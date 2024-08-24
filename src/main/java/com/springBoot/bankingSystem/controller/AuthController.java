package com.springBoot.bankingSystem.controller;

import com.springBoot.bankingSystem.dto.AuthResponseDTO;
import com.springBoot.bankingSystem.dto.LoginDto;
import com.springBoot.bankingSystem.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        String token =  this.authService.login(loginDto.getUsername(),loginDto.getPassword());
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginDto loginDto){
        this.authService.register(loginDto.getUsername(),loginDto.getPassword());
        return new ResponseEntity<>("User Registered", HttpStatus.OK);
    }
}
