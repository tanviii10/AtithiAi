package com.atithiai.controller.order;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.OrderMaster;
import com.atithiai.enums.OrderStatus;
import com.atithiai.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    //CREATE ORDER
    @PostMapping("/create")
    public OrderMaster createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(null, request.getCustomerName());
    }

    //ADD ITEM TO ORDER
    @PostMapping("/add-item")
    public String addItemToOrder(@RequestBody AddItemRequest request) {

        orderService.addItemToOrder(
                request.getOrderId(),
                request.getMenuItemId(),
                request.getQuantity()
        );

        return "Item added successfully";
    }

    //GET ORDER BY ID
    @GetMapping("/{orderId}")
    public OrderMaster getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    //UPDATE ORDER STATUS
    @PutMapping("/{orderId}/status")
    public String updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateStatusRequest request) {

        orderService.updateOrderStatus(orderId, request.getStatus());
        return "Order status updated";
    }
    
    //KITCHEN: GET ACTIVE ORDERS
    @GetMapping("/active")
    public List<OrderMaster> getActiveOrders() {
        return orderService.getActiveOrders();
    }

    //KITCHEN: MARK ORDER IN PROGRESS
    @PutMapping("/{orderId}/start")
    public String startOrder(@PathVariable Long orderId) {
        orderService.updateOrderStatus(orderId, OrderStatus.IN_PROGRESS);
        return "Order moved to IN_PROGRESS";
    }

    //KITCHEN: COMPLETE ORDER
    @PutMapping("/{orderId}/complete")
    public String completeOrder(@PathVariable Long orderId) {
        orderService.updateOrderStatus(orderId, OrderStatus.COMPLETED);
        return "Order COMPLETED";
    }

}
