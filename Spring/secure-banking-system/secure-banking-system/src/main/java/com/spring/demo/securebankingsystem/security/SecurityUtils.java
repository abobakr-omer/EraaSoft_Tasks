package com.spring.demo.securebankingsystem.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/** Small helpers to read the logged-in user from the SecurityContext. */
public class SecurityUtils {

    private SecurityUtils() {
    }

    /** Who is logged in? Always comes from the SecurityContext, never from the request body or URL. */
    public static CustomUserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails user)) {
            throw new AuthenticationCredentialsNotFoundException("You are not logged in");
        }
        return user;
    }

    public static boolean hasRole(String roleName) {
        return currentUser().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + roleName));
    }

    /**
     * Bank staff may see every account / transfer.
     * A normal CUSTOMER may only see their own ("own only" in the rules matrix).
     */
    public static boolean isStaff() {
        return hasRole("ADMIN") || hasRole("BANK_MANAGER") || hasRole("AUDITOR");
    }
}
