package com.spring.demo.securebankingsystem.dto;

import com.spring.demo.securebankingsystem.enums.AccountStatus;

import java.math.BigDecimal;

public record AccountDto(Long id, String accountNumber, BigDecimal balance, String currency,
                         AccountStatus status, Long customerId) {
}
