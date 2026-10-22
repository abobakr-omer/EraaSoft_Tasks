package com.spring.demo.securebankingsystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotNull Long customerId,
        @NotBlank @Size(min = 3, max = 3, message = "must be 3 letters, e.g. USD") String currency,
        @DecimalMin("0.00") BigDecimal initialBalance) {
}
