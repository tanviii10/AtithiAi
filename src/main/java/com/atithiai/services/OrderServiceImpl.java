package com.atithiai.services;

import java.math.BigDecimal;
import java.util.List;

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

    //CREATE ORDER
    @Override
    public OrderMaster createOrder(Long tableId, String customerName) {

        OrderMaster order = new OrderMaster();

        // Table linking will be handled later
        order.setRestaurantTable(null);

        order.setCustomerName(customerName);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmount(BigDecimal.ZERO);

        return orderMasterRepository.save(order);
    }

    //ADD ITEM TO ORDER
    @Override
    public void addItemToOrder(Long orderId, Long menuItemId, int quantity) {

        OrderMaster order =
                orderMasterRepository.findById(orderId)
                        .orElseThrow(() -> new RuntimeException("Order not found"));

        MenuItem menuItem =
                menuItemRepository.findById(menuItemId)
                        .orElseThrow(() -> new RuntimeException("Menu item not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderMaster(order);
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(menuItem.getPrice());

        orderItemRepository.save(orderItem);

        // BigDecimal calculation
        BigDecimal itemTotal =
                menuItem.getPrice().multiply(BigDecimal.valueOf(quantity));

        BigDecimal updatedTotal =
                order.getTotalAmount().add(itemTotal);

        order.setTotalAmount(updatedTotal);
        orderMasterRepository.save(order);
    }

    //UPDATE ORDER STATUS
    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {

        OrderMaster order =
                orderMasterRepository.findById(orderId)
                        .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        orderMasterRepository.save(order);
    }

    //GET ORDER BY ID
    @Override
    public OrderMaster getOrderById(Long orderId) {
        return orderMasterRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    @Override
    public List<OrderMaster> getActiveOrders() {
        return orderMasterRepository.findByStatusIn(
                List.of(OrderStatus.CREATED, OrderStatus.IN_PROGRESS)
        );
    }
}
