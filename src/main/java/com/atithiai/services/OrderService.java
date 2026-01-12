package com.atithiai.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atithiai.entities.OrderItem;
import com.atithiai.entities.OrderMaster;
import com.atithiai.repositories.OrderItemRepository;
import com.atithiai.repositories.OrderMasterRepository;

@Service
public class OrderService {

	private final OrderMasterRepository orderMasterRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderMasterRepository orderMasterRepository,
                        OrderItemRepository orderItemRepository) {
        this.orderMasterRepository = orderMasterRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderMaster createOrder(OrderMaster orderMaster) {
        orderMaster.setStatus("CREATED");
        return orderMasterRepository.save(orderMaster);
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public BigDecimal calculateTotalAmount(List<OrderItem> items) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

    public OrderMaster updateOrderStatus(Long orderId, String status) {
        OrderMaster order = orderMasterRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderMasterRepository.save(order);
    }
}
