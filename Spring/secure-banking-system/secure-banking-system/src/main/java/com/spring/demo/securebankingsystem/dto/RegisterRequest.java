package com.spring.demo.securebankingsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Clients are NOT allowed to send "roles" or "role". These two fields exist only
 * so we can detect an attacker sending them and answer with 400 (attack scenario 1).
 */
public record RegisterRequest(
        @NotBlank String fullName,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6, message = "must be at least 6 characters") String password,
        String phone,
        Object roles,
        Object role) {
}
