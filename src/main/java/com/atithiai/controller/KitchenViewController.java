package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.atithiai.services.OrderService;

@Controller
public class KitchenViewController {

	private final OrderService orderService;

    public KitchenViewController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/kitchen")
    public String kitchenDashboard(Model model) {
        model.addAttribute("orders", orderService.getActiveOrders());
        return "kitchen/kitchen-dashboard";
    }
}
