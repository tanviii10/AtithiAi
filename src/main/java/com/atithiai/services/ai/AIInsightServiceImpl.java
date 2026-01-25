package com.atithiai.services.ai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public class AIInsightServiceImpl implements AIInsightService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String AI_BASE_PATH =
            System.getProperty("user.dir") + "/atithiai-ai-module/ai_output/";

    private File getFile(String name) {
        File file = new File(AI_BASE_PATH + name);

        if (!file.exists()) {
            throw new RuntimeException("AI file not found: " + file.getAbsolutePath());
        }

        return file;
    }

    // 1️⃣ Peak Hours
    @Override
    public Map<String, Object> getPeakHours() {
        try {
            return objectMapper.readValue(
                    getFile("peak_hours.json"),
                    new TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load peak hours AI data", e);
        }
    }

    // 2️⃣ Food Demand
    @Override
    public Map<String, String> getFoodDemand() {
        try {
            return objectMapper.readValue(
                    getFile("food_demand.json"),
                    new TypeReference<Map<String, String>>() {}
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load food demand AI data", e);
        }
    }

    // 3️⃣ Menu Optimization
    @Override
    public Map<String, Object> getMenuOptimizationInsights() {
        try {
            return objectMapper.readValue(
                    getFile("menu_optimization.json"),
                    new TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load menu optimization AI data", e);
        }
    }

    // 4️⃣ Dish Explanation
    @Override
    public Map<String, Object> getDishExplanation(String dishName) {
        try {
            return objectMapper.readValue(
                    getFile("dish_explanation.json"),
                    new TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load dish explanation AI data", e);
        }
    }
}
