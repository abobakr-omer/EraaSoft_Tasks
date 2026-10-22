package com.spring.demo.securebankingsystem.service;

import com.spring.demo.securebankingsystem.dto.*;
import com.spring.demo.securebankingsystem.exception.ApiException;
import com.spring.demo.securebankingsystem.model.Customer;
import com.spring.demo.securebankingsystem.model.Role;
import com.spring.demo.securebankingsystem.repo.CustomerRepository;
import com.spring.demo.securebankingsystem.repo.PermissionRepository;
import com.spring.demo.securebankingsystem.repo.RoleRepository;
import com.spring.demo.securebankingsystem.util.DtoMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, RoleRepository roleRepository,
                           PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------- permission-based (fine-grained) ----------

    @PreAuthorize("hasAuthority('CUSTOMER_READ')")
    public CustomerDto getById(Long id) {
        return DtoMapper.toDto(findCustomer(id));
    }

    @PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
    public CustomerDto update(Long id, UpdateCustomerRequest request) {
        Customer customer = findCustomer(id);
        customer.setFullName(request.fullName());
        customer.setPhoneNumber(request.phone());
        return DtoMapper.toDto(customerRepository.save(customer));
    }

    // ---------- role-based (high level): ADMIN only ----------

    @PreAuthorize("hasRole('ADMIN')")
    public CustomerDto create(CreateCustomerRequest request) {
        if (customerRepository.existsByEmailIgnoreCase(request.email())) {
            throw ApiException.conflict("Email is already registered");
        }

        List<String> roleNames = request.roles() == null || request.roles().isEmpty()
                ? List.of("CUSTOMER")
                : request.roles();

        Customer customer = new Customer();
        customer.setFullName(request.fullName());
        customer.setEmail(request.email().toLowerCase());
        customer.setPassword(passwordEncoder.encode(request.password()));
        customer.setPhoneNumber(request.phone());
        customer.setRoles(findRoles(roleNames));
        return DtoMapper.toDto(customerRepository.save(customer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CustomerDto assignRoles(Long id, AssignRolesRequest request) {
        Customer customer = findCustomer(id);
        customer.setRoles(findRoles(request.roles()));
        return DtoMapper.toDto(customerRepository.save(customer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CustomerDto block(Long id) {
        Customer customer = findCustomer(id);
        customer.setBlocked(true);
        return DtoMapper.toDto(customerRepository.save(customer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CustomerDto unblock(Long id) {
        Customer customer = findCustomer(id);
        customer.setBlocked(false);
        return DtoMapper.toDto(customerRepository.save(customer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CustomerDto disable(Long id) {
        Customer customer = findCustomer(id);
        customer.setEnabled(false);
        return DtoMapper.toDto(customerRepository.save(customer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CustomerDto enable(Long id) {
        Customer customer = findCustomer(id);
        customer.setEnabled(true);
        return DtoMapper.toDto(customerRepository.save(customer));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDto> listRoles() {
        return roleRepository.findAll().stream().map(DtoMapper::toDto).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionDto> listPermissions() {
        return permissionRepository.findAll().stream().map(DtoMapper::toDto).toList();
    }

    // ---------- helpers ----------

    private Customer findCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Customer " + id + " not found"));
    }

    private List<Role> findRoles(List<String> roleNames) {
        List<Role> roles = new ArrayList<>();
        for (String name : roleNames) {
            Role role = roleRepository.findByName(name.toUpperCase())
                    .orElseThrow(() -> ApiException.badRequest("Unknown role: " + name));
            roles.add(role);
        }
        return roles;
    }
}
