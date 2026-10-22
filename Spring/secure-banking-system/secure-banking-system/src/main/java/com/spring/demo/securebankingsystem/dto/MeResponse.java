package com.spring.demo.securebankingsystem.dto;

import java.util.List;

public record MeResponse(Long id, String email, String fullName, List<String> roles, List<String> authorities) {
}
