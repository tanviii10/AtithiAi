package com.atithiai.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.MenuItem;
import com.atithiai.entities.OrderMaster;
import com.atithiai.services.MenuItemService;
import com.atithiai.services.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class CustomerOrderController {

    private final MenuItemService menuItemService;
    private final OrderService orderService;

    public CustomerOrderController(MenuItemService menuItemService,
                                   OrderService orderService) {
        this.menuItemService = menuItemService;
        this.orderService = orderService;
    }

   
    @GetMapping("/menu")
    public String showMenu(
            @RequestParam String category,
            Model model) {

        List<MenuItem> menuItems = menuItemService.getByCategory(category);

        model.addAttribute("menuItems", menuItems);
        model.addAttribute("category", category);

        return "order/order-menu";
    }

   
    @PostMapping("/add")
    public String addItemToCart(
            @RequestParam Long menuItemId,
            @RequestParam int quantity,
            @RequestParam String category,
            HttpSession session) {

        Long orderId = (Long) session.getAttribute("orderId");

        // Create order if not exists
        if (orderId == null) {
            OrderMaster order = orderService.createOrder(null, "Guest");
            orderId = order.getId();
            session.setAttribute("orderId", orderId);
        }

        orderService.addItemToOrder(orderId, menuItemId, quantity);

        return "redirect:/order/menu?category=" + category;
    }

   
    @GetMapping("/review")
    public String reviewOrder(HttpSession session, Model model) {

        Long orderId = (Long) session.getAttribute("orderId");

        if (orderId == null) {
            return "redirect:/";
        }

        OrderMaster order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);

        return "order/order-details";
    }
}
