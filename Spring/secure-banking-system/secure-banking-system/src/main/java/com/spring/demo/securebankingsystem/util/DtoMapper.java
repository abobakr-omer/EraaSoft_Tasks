package com.spring.demo.securebankingsystem.util;

import com.spring.demo.securebankingsystem.dto.*;
import com.spring.demo.securebankingsystem.model.*;

/** Converts entities to DTOs by hand (easy to read, no magic). */
public class DtoMapper {

    private DtoMapper() {
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.isEnabled(),
                customer.isBlocked(),
                customer.getCreatedAt(),
                customer.getRoles().stream().map(Role::getName).toList());
    }

    public static RoleDto toDto(Role role) {
        return new RoleDto(role.getId(), role.getName(),
                role.getPermissions().stream().map(Permission::getName).toList());
    }

    public static PermissionDto toDto(Permission permission) {
        return new PermissionDto(permission.getId(), permission.getName());
    }

    public static AccountDto toDto(Account account) {
        return new AccountDto(account.getId(), account.getAccountNumber(), account.getBalance(),
                account.getCurrency(), account.getStatus(), account.getCustomer().getId());
    }

    public static TransferDto toDto(Transfer transfer) {
        return new TransferDto(transfer.getId(), transfer.getFromAccount().getId(),
                transfer.getToAccount().getId(), transfer.getAmount(), transfer.getStatus(),
                transfer.getCreatedAt(), transfer.getCreatedBy().getId());
    }
}
