package com.spring.demo.securebankingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Kept in its own class so it does not create a circular dependency with SecurityConfig. */
@Configuration
public class PasswordConfig {

    // Raw Password -> PasswordEncoder -> BCrypt hash -> Database
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
