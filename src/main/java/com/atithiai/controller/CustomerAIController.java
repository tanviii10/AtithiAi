package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atithiai.services.ai.AIInsightService;

@Controller
@RequestMapping("/ai")
public class CustomerAIController {

	private final AIInsightService aiInsightService;

    public CustomerAIController(AIInsightService aiInsightService) {
        this.aiInsightService = aiInsightService;
    }

    // Dish Explanation
    @GetMapping("/dish-explanation")
    public String dishExplanation(
            @RequestParam(required = false) String dishName,
            Model model) {

        if (dishName != null && !dishName.isBlank()) {
            model.addAttribute(
                "dish",
                aiInsightService.getDishExplanation(dishName)
            );
        }

        return "customer/ai/dish-explanation";
    }

    // Menu Optimization
    @GetMapping("/menu-optimization")
    public String menuOptimization(Model model) {

        model.addAttribute(
            "insights",
            aiInsightService.getMenuOptimizationInsights()
        );

        return "customer/ai/menu-optimization";
    }
}
