package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atithiai.entities.OrderMaster;
import com.atithiai.entities.Payment;
import com.atithiai.repositories.PaymentRepository;
import com.atithiai.services.OrderService;

@Controller
public class InvoiceController {
	
	private final OrderService orderService;
    private final PaymentRepository paymentRepository;

    public InvoiceController(OrderService orderService,
                             PaymentRepository paymentRepository) {
        this.orderService = orderService;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/invoice/{orderId}")
    public String viewInvoice(@PathVariable Long orderId, Model model) {

        OrderMaster order = orderService.getOrderById(orderId);

        Payment payment = paymentRepository
                .findAll()
                .stream()
                .filter(p -> "ORDER".equals(p.getReferenceType())
                        && p.getReferenceId().equals(orderId))
                .findFirst()
                .orElse(null);

        model.addAttribute("order", order);
        model.addAttribute("payment", payment);

        return "invoice/invoice-view";
    }

}
