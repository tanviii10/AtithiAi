package com.atithiai.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.atithiai.services.OrderService;
import com.atithiai.services.PaymentService;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;

    public PaymentController(PaymentService paymentService,
                             OrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @PostMapping("/payment/start")
    public String startPayment(@RequestParam Long orderId,
                               @RequestParam String paymentMode) {

        paymentService.makePayment(orderId, paymentMode);

        return "redirect:/invoice/view/" + orderId;
    }
    
}
