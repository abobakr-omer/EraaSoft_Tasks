package com.spring.demo.securebankingsystem.dto;

import com.spring.demo.securebankingsystem.enums.TransferStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferDto(Long id, Long fromAccountId, Long toAccountId, BigDecimal amount,
                          TransferStatus status, LocalDateTime createdAt, Long createdById) {
}
