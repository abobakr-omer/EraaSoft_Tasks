package com.spring.demo.securebankingsystem.controller;

import com.spring.demo.securebankingsystem.dto.*;
import com.spring.demo.securebankingsystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Everything under /admin is for ADMIN only (SecurityConfig + @PreAuthorize in the service). */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CustomerService customerService;

    public AdminController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(request));
    }

    @PutMapping("/customers/{id}/roles")
    public CustomerDto assignRoles(@PathVariable Long id, @Valid @RequestBody AssignRolesRequest request) {
        return customerService.assignRoles(id, request);
    }

    @PutMapping("/customers/{id}/block")
    public CustomerDto block(@PathVariable Long id) {
        return customerService.block(id);
    }

    @PutMapping("/customers/{id}/unblock")
    public CustomerDto unblock(@PathVariable Long id) {
        return customerService.unblock(id);
    }

    @PutMapping("/customers/{id}/disable")
    public CustomerDto disable(@PathVariable Long id) {
        return customerService.disable(id);
    }

    @PutMapping("/customers/{id}/enable")
    public CustomerDto enable(@PathVariable Long id) {
        return customerService.enable(id);
    }

    @GetMapping("/roles")
    public List<RoleDto> roles() {
        return customerService.listRoles();
    }

    @GetMapping("/permissions")
    public List<PermissionDto> permissions() {
        return customerService.listPermissions();
    }
}
