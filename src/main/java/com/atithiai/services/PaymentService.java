package com.atithiai.services;

import org.springframework.stereotype.Service;

import com.atithiai.entities.Payment;
@Service
public interface PaymentService {
    Payment makePayment(Long orderId, String paymentMode);
}

