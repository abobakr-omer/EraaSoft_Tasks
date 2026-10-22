package com.spring.demo.securebankingsystem.config;

import com.spring.demo.securebankingsystem.security.BankAuthenticationProvider;
import com.spring.demo.securebankingsystem.security.RestAccessDeniedHandler;
import com.spring.demo.securebankingsystem.security.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity   // turns on @PreAuthorize in the service classes
public class SecurityConfig {

    private final BankAuthenticationProvider authenticationProvider;
    private final RestAuthenticationEntryPoint authenticationEntryPoint;
    private final RestAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(BankAuthenticationProvider authenticationProvider,
                          RestAuthenticationEntryPoint authenticationEntryPoint,
                          RestAccessDeniedHandler accessDeniedHandler) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // REST API with Basic auth (no browser forms/cookies), so CSRF is not needed
                .csrf(csrf -> csrf.disable())

                // No HTTP session: the email + password are checked on every request.
                // This means a blocked/disabled customer is rejected immediately.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Use our own provider
                .authenticationProvider(authenticationProvider)

                // Login method: HTTP Basic, with our custom 401 response
                .httpBasic(basic -> basic.authenticationEntryPoint(authenticationEntryPoint))

                // Custom 401 and 403 responses
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // role-based (high level): only ADMIN enters /admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // everything else needs a login; fine-grained permissions are checked with @PreAuthorize
                        .requestMatchers("/auth/me", "/accounts/**", "/transfers/**", "/customers/**", "/reports/**").authenticated()
                        // anything not listed above is denied
                        .anyRequest().denyAll());

        return http.build();
    }
}
