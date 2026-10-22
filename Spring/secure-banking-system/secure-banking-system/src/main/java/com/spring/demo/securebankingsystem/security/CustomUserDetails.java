package com.spring.demo.securebankingsystem.security;

import com.spring.demo.securebankingsystem.model.Customer;
import com.spring.demo.securebankingsystem.model.Permission;
import com.spring.demo.securebankingsystem.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * How Spring Security sees our Customer.
 * All data is copied in the constructor, so this class never touches the database later.
 *
 * Authorities example for ADMIN:  ROLE_ADMIN, ACCOUNT_READ, ACCOUNT_DELETE, TRANSFER_APPROVE ...
 */
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String fullName;
    private final String email;
    private final String password;
    private final boolean enabled;
    private final boolean blocked;
    private final List<String> roleNames;
    private final Set<GrantedAuthority> authorities = new LinkedHashSet<>();

    // Call this inside a transaction, because roles and permissions are lazy.
    public CustomUserDetails(Customer customer) {
        this.id = customer.getId();
        this.fullName = customer.getFullName();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.enabled = customer.isEnabled();
        this.blocked = customer.isBlocked();
        this.roleNames = customer.getRoles().stream().map(Role::getName).toList();

        for (Role role : customer.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));   // role  -> ROLE_ADMIN
            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));    // permission as-is
            }
        }
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /** The username in our system is the email. */
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !blocked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
