package com.atithiai.services;

import org.springframework.stereotype.Service;

import com.atithiai.entities.Payment;
import com.atithiai.repositories.PaymentRepository;

@Service
public interface PaymentService {
    Payment makePayment(Long orderId, String paymentMode);
}

