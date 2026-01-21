package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atithiai.repositories.MenuItemRepository;
import com.atithiai.repositories.OrderItemRepository;
import com.atithiai.repositories.PaymentRepository;
import com.atithiai.repositories.UserAccountRepository;


@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

	private final MenuItemRepository menuRepo;
    private final OrderItemRepository orderRepo;
    private final PaymentRepository paymentRepo;
    private final UserAccountRepository userRepo;

    public AdminDashboardController(
            MenuItemRepository menuRepo,
            OrderItemRepository orderRepo,
            PaymentRepository paymentRepo,
            UserAccountRepository userRepo) {

        this.menuRepo = menuRepo;
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("menuCount", menuRepo.count());
        model.addAttribute("orderCount", orderRepo.count());
        model.addAttribute("revenue", paymentRepo.getTotalRevenue());
        model.addAttribute("customerCount", userRepo.countByRole("CUSTOMER"));

        return "admin/dashboard";
    }
}
