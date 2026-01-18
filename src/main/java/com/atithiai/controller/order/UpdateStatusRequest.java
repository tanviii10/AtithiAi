package com.atithiai.controller.order;

import com.atithiai.enums.OrderStatus;

public class UpdateStatusRequest {

    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
}