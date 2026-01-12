package com.atithiai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.OrderItem;
import com.atithiai.entities.OrderMaster;
import com.atithiai.services.OrderService;


@RestController
@RequestMapping("/api/order")
public class OrderController {

	private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderMaster> createOrder(@RequestBody OrderMaster order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PostMapping("/item")
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(orderService.addOrderItem(orderItem));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderMaster> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

}
