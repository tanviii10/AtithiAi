package com.atithiai.services;

import java.util.List;

import com.atithiai.entities.OrderMaster;
import com.atithiai.enums.OrderStatus;

public interface OrderService {

    OrderMaster createOrder(Long tableId, String customerName);

    void addItemToOrder(Long orderId, Long menuItemId, int quantity);

    void updateOrderStatus(Long orderId, OrderStatus status);

    OrderMaster getOrderById(Long orderId);
    
    List<OrderMaster> getActiveOrders();
}
