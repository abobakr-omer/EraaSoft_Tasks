package com.spring.demo.securebankingsystem.dto;

import java.time.LocalDateTime;
import java.util.List;

/** Safe view of a customer: no password, no internal security data. */
public record CustomerDto(Long id, String fullName, String email, String phone,
                          boolean enabled, boolean blocked, LocalDateTime createdAt,
                          List<String> roles) {
}
