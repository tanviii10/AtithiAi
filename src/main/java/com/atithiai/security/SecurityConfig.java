package com.atithiai.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // DEV ONLY – Plain text passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                //Public
                .requestMatchers(
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()

                // ADMIN only
                .requestMatchers("/admin/**", "/menu/**")
                .hasRole("ADMIN")

                //CUSTOMER only
                .requestMatchers(
                        "/index",
                        "/menu/**",
                        "/cart/**",
                        "/order/**",
                        "/payment/**",
                        "/invoice/**"
                ).hasRole("CUSTOMER")

                //everything else
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/postLogin", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )

            .exceptionHandling(ex ->
                ex.accessDeniedPage("/access-denied")
            );

        return http.build();
    }
}
