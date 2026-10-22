package com.spring.demo.securebankingsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/** Used by ADMIN only. Roles are optional (default is CUSTOMER). */
public record CreateCustomerRequest(
        @NotBlank String fullName,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password,
        String phone,
        List<String> roles) {
}
