package com.spring.demo.securebankingsystem.dto;

import com.spring.demo.securebankingsystem.enums.AccountStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateAccountRequest(@NotNull AccountStatus status) {
}
