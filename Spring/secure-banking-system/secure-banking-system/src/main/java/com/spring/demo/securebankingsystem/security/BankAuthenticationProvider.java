package com.spring.demo.securebankingsystem.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Checks the email and password.
 * It never returns null: every failure throws an AuthenticationException (-> 401).
 */
@Component
public class BankAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public BankAuthenticationProvider(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 1 + 2: read email and password
        String email = authentication.getName();
        Object credentials = authentication.getCredentials();
        String rawPassword = credentials == null ? "" : credentials.toString();

        // 3 + 4: find the customer and validate it (disabled / blocked are checked inside the service)
        UserDetails user;
        try {
            user = userDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            // Same message as a wrong password, so attackers can't find out which emails exist.
            throw new BadCredentialsException("Invalid email or password");
        }

        // 5: validate the password (raw password vs BCrypt hash)
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        // 6 + 7 + 8: roles, permissions and authorities were already loaded into the user.
        // A customer without any role is not allowed to log in.
        boolean hasRole = user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().startsWith("ROLE_"));
        if (!hasRole) {
            throw new InsufficientAuthenticationException("Customer has no valid role");
        }

        // 9: return an authenticated token. The principal is our CustomUserDetails.
        return UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
