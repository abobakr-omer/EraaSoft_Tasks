package com.spring.demo.securebankingsystem.service;

import com.spring.demo.securebankingsystem.dto.CustomerDto;
import com.spring.demo.securebankingsystem.dto.MeResponse;
import com.spring.demo.securebankingsystem.dto.RegisterRequest;
import com.spring.demo.securebankingsystem.exception.ApiException;
import com.spring.demo.securebankingsystem.model.Customer;
import com.spring.demo.securebankingsystem.model.Role;
import com.spring.demo.securebankingsystem.repo.CustomerRepository;
import com.spring.demo.securebankingsystem.repo.RoleRepository;
import com.spring.demo.securebankingsystem.security.CustomUserDetails;
import com.spring.demo.securebankingsystem.security.SecurityUtils;
import com.spring.demo.securebankingsystem.util.DtoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(CustomerRepository customerRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public CustomerDto register(RegisterRequest request) {
        // Attack scenario 1: the client must NEVER choose a role.
        if (request.roles() != null || request.role() != null) {
            throw ApiException.badRequest("You are not allowed to choose a role during registration");
        }

        if (customerRepository.existsByEmailIgnoreCase(request.email())) {
            throw ApiException.conflict("Email is already registered");
        }

        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new IllegalStateException("CUSTOMER role is missing in the database"));

        Customer customer = new Customer();
        customer.setFullName(request.fullName());
        customer.setEmail(request.email().toLowerCase());
        customer.setPassword(passwordEncoder.encode(request.password()));   // BCrypt, never the raw password
        customer.setPhoneNumber(request.phone());
        customer.getRoles().add(customerRole);                              // always CUSTOMER

        return DtoMapper.toDto(customerRepository.save(customer));
    }

    /** Reads everything from the SecurityContext. No database call and no id from the request. */
    public MeResponse me() {
        CustomUserDetails user = SecurityUtils.currentUser();
        return new MeResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRoleNames(),
                user.getAuthorities().stream().map(authority -> authority.getAuthority()).toList());
    }
}
