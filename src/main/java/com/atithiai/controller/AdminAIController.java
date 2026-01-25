package com.atithiai.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atithiai.services.ai.AIInsightService;

@Controller
@RequestMapping("/admin/ai")
public class AdminAIController {
	
	private final AIInsightService aiInsightService;

    public AdminAIController(AIInsightService aiInsightService) {
        this.aiInsightService = aiInsightService;
    }

    //Peak Hours
    @GetMapping("/peak-hours")
    public String peakHours(Model model) {
    	
    	System.out.println("ADMIN AI CONTROLLER: peak-hours endpoint HIT");

        Map<String, Object> data = aiInsightService.getPeakHours();
        model.addAttribute("peakHours", data.get("peak_hours"));
        model.addAttribute("description", data.get("description"));
        return "admin/ai/peak-hours";
    }

    //Food Demand
    @GetMapping("/food-demand")
    public String foodDemand(Model model) {
        Map<String, String> foodDemand = aiInsightService.getFoodDemand();
        model.addAttribute("foodDemand", foodDemand);
        return "admin/ai/food-demand";
    }

    //Menu Optimization
    @GetMapping("/menu-optimization")
    public String menuOptimization(Model model) {
        Map<String, Object> data =
                aiInsightService.getMenuOptimizationInsights();

        model.addAttribute(
            "insights",
            (List<String>) data.get("menu_optimization_insights")
        );

        return "admin/ai/menu-optimization";
    }

    //Dish Explanation
    @GetMapping("/dish-explanation")
    public String dishExplanation(Model model) {

        Map<String, Object> data =
                aiInsightService.getDishExplanation("Gulab Jamun");

        model.addAttribute("dish", data);
        return "admin/ai/dish-explanation";
    }

    @GetMapping("")
    public String aiDashboard() {
        return "admin/ai/index";
    }

}
