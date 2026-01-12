package com.atithiai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.Payment;
import com.atithiai.services.PaymentService;



@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.savePayment(payment));
    }
}
