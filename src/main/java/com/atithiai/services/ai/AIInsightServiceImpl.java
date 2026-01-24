package com.atithiai.services.ai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class AIInsightServiceImpl implements AIInsightService {

	private static final String PEAK_HOURS_FILE =
            "atithiai-ai-module/ai_output/peak_hours.json";

	private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String AI_OUTPUT_PATH = "ai_output/";
    

    //Peak Hours
    @Override
    public Map<String, Object> getPeakHours() {
        try {
            File file = new File(PEAK_HOURS_FILE);
            return objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read peak hours AI data", e);
        }
    }

    //Food Demand
    @Override
    public Map<String, String> getFoodDemand() {
        try {
            File file = new File("atithiai-ai-module/ai_output/food_demand.json");
            return objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read food demand AI data", e);
        }
    }

    //Menu Optimization
    @Override
    public List<String> getMenuOptimizationInsights() {
        try {
            File file = new File(AI_OUTPUT_PATH + "menu_optimization.json");
            Map<String, List<String>> data =
                    objectMapper.readValue(file, new TypeReference<>() {});
            return data.get("menu_optimization_insights");
        } catch (Exception e) {
            throw new RuntimeException("Failed to read menu optimization AI output", e);
        }
    }

    //Dish Explanation
    @Override
    public Map<String, String> getDishExplanation(String dishName) {
        try {
            File file = new File(AI_OUTPUT_PATH + "dish_explanation.json");
            return objectMapper.readValue(file, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to read dish explanation AI output", e);
        }
    }
}
