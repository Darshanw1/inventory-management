package com.inventory.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                // Disable CSRF because this is a REST API
                .csrf(csrf -> csrf.disable())

                // JWT is stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // API permissions
                .authorizeHttpRequests(auth -> auth

                        // Public authentication APIs
                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login"
                        ).permitAll()

                        // ADMIN only
                        .requestMatchers(
                                "/api/users/**"
                        ).hasRole("ADMIN")

                        // Product management
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST,
                                "/api/products/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.PUT,
                                "/api/products/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE,
                                "/api/products/**"
                        ).hasRole("ADMIN")

                        // Product viewing
                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET,
                                "/api/products/**"
                        ).hasAnyRole("ADMIN", "STAFF")

                        // Warehouse management
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST,
                                "/api/warehouses/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.PUT,
                                "/api/warehouses/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE,
                                "/api/warehouses/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET,
                                "/api/warehouses/**"
                        ).hasAnyRole("ADMIN", "STAFF")

                        // Stock
                        .requestMatchers("/api/stock/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        // Purchases
                        .requestMatchers("/api/purchases/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        // Everything else
                        .anyRequest().authenticated()
                )

                // Add JWT filter before Spring's username/password filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}