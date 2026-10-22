package com.spring.demo.securebankingsystem.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AssignRolesRequest(@NotEmpty List<String> roles) {
}
