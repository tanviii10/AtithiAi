package com.atithiai.services;

import org.springframework.stereotype.Service;

import com.atithiai.entities.Payment;
import com.atithiai.repositories.PaymentRepository;

@Service
public class PaymentService {
	
	private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        payment.setStatus("SUCCESS");
        return paymentRepository.save(payment);
    }
}
