package com.atithiai.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.atithiai.entities.OrderMaster;
import com.atithiai.entities.Payment;
import com.atithiai.enums.OrderStatus;
import com.atithiai.enums.PaymentStatus;
import com.atithiai.repositories.OrderMasterRepository;
import com.atithiai.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository paymentRepository;
    private final OrderMasterRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              OrderMasterRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Payment makePayment(Long orderId, String paymentMode) {

        OrderMaster order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setReferenceType("ORDER");        
        payment.setReferenceId(orderId);
        payment.setPaymentMode(paymentMode);
        payment.setAmount(order.getTotalAmount());
        payment.setStatus(PaymentStatus.PAID);
        payment.setPaymentDate(LocalDateTime.now());

        // Business rule: payment completes order
        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);

        return paymentRepository.save(payment);
    }

}
