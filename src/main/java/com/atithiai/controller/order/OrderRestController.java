package com.atithiai.controller.order;

import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.OrderMaster;
import com.atithiai.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1️⃣ CREATE ORDER
    @PostMapping("/create")
    public OrderMaster createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(null, request.getCustomerName());
    }

    // 2️⃣ ADD ITEM TO ORDER
    @PostMapping("/add-item")
    public String addItemToOrder(@RequestBody AddItemRequest request) {

        orderService.addItemToOrder(
                request.getOrderId(),
                request.getMenuItemId(),
                request.getQuantity()
        );

        return "Item added successfully";
    }

    // 3️⃣ GET ORDER BY ID
    @GetMapping("/{orderId}")
    public OrderMaster getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    // 4️⃣ UPDATE ORDER STATUS
    @PutMapping("/{orderId}/status")
    public String updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateStatusRequest request) {

        orderService.updateOrderStatus(orderId, request.getStatus());
        return "Order status updated";
    }
}
