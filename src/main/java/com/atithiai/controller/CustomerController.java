package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

	@GetMapping("/index")
    public String customerHome() {
        return "public/index";
    }
}
