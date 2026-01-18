package com.atithiai.services;

import org.springframework.stereotype.Service;

import com.atithiai.entities.MenuItem;
import com.atithiai.entities.OrderItem;
import com.atithiai.entities.OrderMaster;
import com.atithiai.enums.OrderStatus;
import com.atithiai.repositories.MenuItemRepository;
import com.atithiai.repositories.OrderItemRepository;
import com.atithiai.repositories.OrderMasterRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMasterRepository orderMasterRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderServiceImpl(OrderMasterRepository orderMasterRepository,
                            OrderItemRepository orderItemRepository,
                            MenuItemRepository menuItemRepository) {

        this.orderMasterRepository = orderMasterRepository;
        this.orderItemRepository = orderItemRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public OrderMaster createOrder(Long tableId, String customerName) {

        OrderMaster order = new OrderMaster();
        order.setRestaurantTableId(tableId);
        order.setCustomerName(customerName);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmount(0.0);

        return orderMasterRepository.save(order);
    }

    @Override
    public void addItemToOrder(Long orderId, Long menuItemId, int quantity) {

        OrderMaster order =
                orderMasterRepository.findById(orderId).orElseThrow();

        MenuItem menuItem =
                menuItemRepository.findById(menuItemId).orElseThrow();

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(menuItem.getPrice());

        orderItemRepository.save(orderItem);

        double updatedTotal =
                order.getTotalAmount() + (menuItem.getPrice() * quantity);

        order.setTotalAmount(updatedTotal);
        orderMasterRepository.save(order);
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {

        OrderMaster order =
                orderMasterRepository.findById(orderId).orElseThrow();

        order.setStatus(status);
        orderMasterRepository.save(order);
    }

    @Override
    public OrderMaster getOrderById(Long orderId) {
        return orderMasterRepository.findById(orderId).orElseThrow();
    }
}
