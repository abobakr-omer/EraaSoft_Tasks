package com.spring.demo.securebankingsystem.dto;

import java.util.List;

public record RoleDto(Long id, String name, List<String> permissions) {
}
