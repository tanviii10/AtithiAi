package com.atithiai.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostLoginController {

	@GetMapping("/postLogin")
    public String redirectAfterLogin(Authentication authentication) {

        for (GrantedAuthority authority : authentication.getAuthorities()) {

            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return "redirect:/admin/dashboard";
            }

            if (authority.getAuthority().equals("ROLE_CUSTOMER")) {
                return "redirect:/index";
            }
        }

        return "redirect:/login";
    }
}
