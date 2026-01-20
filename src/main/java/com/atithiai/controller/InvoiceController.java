package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atithiai.entities.OrderMaster;
import com.atithiai.entities.Payment;
import com.atithiai.services.OrderService;
import com.atithiai.services.PaymentService;

@Controller
public class InvoiceController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    public InvoiceController(OrderService orderService,
                             PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @GetMapping("/invoice/view/{orderId}")
    public String viewInvoice(@PathVariable Long orderId, Model model) {

        OrderMaster order = orderService.getOrderById(orderId);
        Payment payment = paymentService.getPaymentByOrderId(orderId);

        model.addAttribute("order", order);
        model.addAttribute("payment", payment);

        return "invoice/invoice-view";
    }

}
