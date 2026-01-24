package com.atithiai.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.atithiai.services.ai.AIInsightService;

@Controller
public class AdminAIController {
	
	private final AIInsightService aiInsightService;

    public AdminAIController(AIInsightService aiInsightService) {
        this.aiInsightService = aiInsightService;
    }

    @GetMapping("/admin/ai/peak-hours")
    public String viewPeakHours(Model model) {

        Map<String, Object> data = aiInsightService.getPeakHours();

        model.addAttribute("peakHours", data.get("peak_hours"));
        model.addAttribute("description", data.get("description"));

        return "admin/ai/peak-hours";
    }
    
    @GetMapping("/admin/ai/food-demand")
    public String viewFoodDemand(Model model) {

        Map<String, String> demandData = aiInsightService.getFoodDemand();

        model.addAttribute("demandData", demandData);

        return "admin/ai/food-demand";
    }


}
