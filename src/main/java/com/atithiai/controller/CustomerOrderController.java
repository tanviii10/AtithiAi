package com.atithiai.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.MenuItem;
import com.atithiai.entities.OrderMaster;
import com.atithiai.services.MenuItemService;
import com.atithiai.services.OrderService;

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

}
