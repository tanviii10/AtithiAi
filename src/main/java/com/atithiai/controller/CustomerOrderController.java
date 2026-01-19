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
public class CustomerOrderController {
	
	private final MenuItemService menuItemService;
    private final OrderService orderService;

    public CustomerOrderController(MenuItemService menuItemService,
                                   OrderService orderService) {
        this.menuItemService = menuItemService;
        this.orderService = orderService;
    }

    //Show menu & start order
    @GetMapping("/order")
    public String showMenu(Model model) {
        List<MenuItem> menu = menuItemService.getAllMenuItems();
        model.addAttribute("menu", menu);
        return "order/order-menu";
    }

    //Create order
    @PostMapping("/order/create")
    public String createOrder(@RequestParam String customerName) {
        OrderMaster order = orderService.createOrder(null, customerName);
        return "redirect:/order/" + order.getId();
    }

    //Order detail page
    @GetMapping("/order/{orderId}")
    public String orderDetails(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrderById(orderId));
        model.addAttribute("menu", menuItemService.getAllMenuItems());
        return "order/order-details";
    }

    //Add item to order
    @PostMapping("/order/{orderId}/add")
    public String addItem(@PathVariable Long orderId,
                          @RequestParam Long menuItemId,
                          @RequestParam int quantity) {

        orderService.addItemToOrder(orderId, menuItemId, quantity);
        return "redirect:/order/" + orderId;
    }
    
    @GetMapping("/order/menu")
    public String showMenuByCategory(
            @RequestParam String category,
            Model model) {

        List<MenuItem> items =
                menuItemService.getAvailableItemsByCategory(category);

        model.addAttribute("items", items);
        model.addAttribute("category", category);

        return "order/order-menu";
    }
    
    @PostMapping("/order/start")
    public String startOrder(
            @RequestParam String customerName,
            HttpSession session) {

        OrderMaster order = orderService.createOrder(customerName);
        session.setAttribute("orderId", order.getId());

        return "redirect:/order/menu?category=STARTER";
    }
    
    @PostMapping("/order/add-item")
    public String addItem(
            @RequestParam Long menuItemId,
            @RequestParam int quantity,
            HttpSession session) {

        Long orderId = (Long) session.getAttribute("orderId");

        orderService.addItemToOrder(orderId, menuItemId, quantity);

        return "redirect:/order/menu";
    }
    
    @GetMapping("/order/review")
    public String reviewOrder(HttpSession session, Model model) {

        Long orderId = (Long) session.getAttribute("orderId");
        OrderMaster order = orderService.getOrderById(orderId);

        model.addAttribute("order", order);
        return "order/order-details";
    }

}
