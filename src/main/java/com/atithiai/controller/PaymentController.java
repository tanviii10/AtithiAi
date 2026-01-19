package com.atithiai.controller;

import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.Payment;
import com.atithiai.services.PaymentService;



@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public Payment makePayment(@RequestParam Long orderId,
                               @RequestParam String mode) {
        return paymentService.makePayment(orderId, mode);
    }
}
