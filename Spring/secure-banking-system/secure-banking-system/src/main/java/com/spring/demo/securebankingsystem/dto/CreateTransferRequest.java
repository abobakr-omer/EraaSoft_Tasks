package com.spring.demo.securebankingsystem.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/** No "owner" field on purpose: the owner is always the logged-in user. */
public record CreateTransferRequest(@NotNull Long fromAccountId, @NotNull Long toAccountId, @NotNull BigDecimal amount) {
}
