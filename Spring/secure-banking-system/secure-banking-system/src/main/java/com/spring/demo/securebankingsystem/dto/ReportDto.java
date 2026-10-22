package com.spring.demo.securebankingsystem.dto;

import java.math.BigDecimal;

public record ReportDto(long totalCustomers, long totalAccounts, long totalTransfers,
                        long pendingTransfers, BigDecimal totalBalance) {
}
