package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/dish-explanation")
    public String dishExplanationPage(Model model) {

        model.addAttribute("dishes",
                aiInsightService.getAllDishNames());

        return "customer/ai/dish-explanation";
    }

    @PostMapping("/dish-explanation")
    public String dishExplanationResult(
            @RequestParam String dishName,
            Model model) {

        model.addAttribute("dishes",
                aiInsightService.getAllDishNames());

        model.addAttribute("selectedDish", dishName);

        model.addAttribute("info",
                aiInsightService.getDishExplanation(dishName));

        return "customer/ai/dish-explanation";
    }

    @GetMapping("/menu-optimization")
    public String menuOptimization(Model model) {

        model.addAttribute("insights",
                aiInsightService.getMenuOptimizationInsights());

        return "customer/ai/menu-optimization";
    }
}
