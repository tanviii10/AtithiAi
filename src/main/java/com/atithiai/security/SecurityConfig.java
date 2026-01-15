package com.atithiai.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())

	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/", 
	                "/login",
	                "/css/**",
	                "/images/**"
	            ).permitAll()
	            .requestMatchers("/menu/**").hasAnyRole("ADMIN", "STAFF")
	            .anyRequest().authenticated()
	        )

	        .formLogin(form -> form
	            .loginPage("/login")              // GET
	            .loginProcessingUrl("/login")     // POST
	            .defaultSuccessUrl("/menu/list", true)
	            .failureUrl("/login?error=true")
	            .permitAll()
	        )

	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	        );

	    return http.build();
	}

}
