package com.spring.demo.securebankingsystem.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest(@NotBlank String fullName, String phone) {
}
