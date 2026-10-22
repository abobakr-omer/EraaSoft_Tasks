package com.spring.demo.securebankingsystem.controller;

import com.spring.demo.securebankingsystem.dto.CustomerDto;
import com.spring.demo.securebankingsystem.dto.MeResponse;
import com.spring.demo.securebankingsystem.dto.RegisterRequest;
import com.spring.demo.securebankingsystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @GetMapping("/me")
    public MeResponse me() {
        return authService.me();
    }
}
