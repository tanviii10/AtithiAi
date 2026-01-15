package com.atithiai.controller.publicsite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/login")
    public String loginPage() {
        return "public/login";
    }
}
