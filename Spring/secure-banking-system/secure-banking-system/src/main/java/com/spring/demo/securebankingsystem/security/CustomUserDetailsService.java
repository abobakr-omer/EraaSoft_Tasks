package com.spring.demo.securebankingsystem.security;

import com.spring.demo.securebankingsystem.model.Customer;
import com.spring.demo.securebankingsystem.repo.CustomerRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1 + 2: receive the email and search for the customer
        Customer customer = customerRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));

        // 3: check the status
        if (!customer.isEnabled()) {
            throw new DisabledException("Customer is disabled");
        }
        if (customer.isBlocked()) {
            throw new LockedException("Customer is blocked");
        }

        // 4: convert to CustomUserDetails (this also builds the authorities)
        return new CustomUserDetails(customer);
    }
}
