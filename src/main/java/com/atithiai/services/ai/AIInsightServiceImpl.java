package com.atithiai.services.ai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
import java.util.Set;

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

    //Peak Hours
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

    //Food Demand
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

    //Menu Optimization
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
    
    @Override
    public Set<String> getAllDishNames() {
        try {
            File file = getFile("dish_explanation.json");
            Map<String, Object> data =
                    objectMapper.readValue(file, Map.class);
            return data.keySet();
        } catch (Exception e) {
            throw new RuntimeException("Unable to load dish list", e);
        }
    }

    //Dish Explanation
    @Override
    public Map<String, Object> getDishExplanation(String dishName) {
        try {
            File file = getFile("dish_explanation.json");
            Map<String, Map<String, Object>> data =
                    objectMapper.readValue(file, Map.class);

            return data.getOrDefault(dishName, Map.of(
                    "error", "Dish information not found"
            ));
        } catch (Exception e) {
            throw new RuntimeException("Unable to read dish explanation", e);
        }
    }
}
