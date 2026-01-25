package com.atithiai.services.ai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public class AIInsightServiceImpl implements AIInsightService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${ai.output.path}")
    private String aiOutputPath;

    private File getAIFile(String fileName) {
        return new File(aiOutputPath + File.separator + fileName);
    }

    @Override
    public Map<String, Object> getPeakHours() {
    	
    	System.out.println("USER DIR =  " + System.getProperty("user.dir"));

        try {
            return objectMapper.readValue(
                    getAIFile("peak_hours.json"),
                    Map.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Peak hours AI file not found", e);
        }
    }

    @Override
    public Map<String, String> getFoodDemand() {
        try {
            return objectMapper.readValue(
                    getAIFile("food_demand.json"),
                    new TypeReference<Map<String, String>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("Food demand AI file not found", e);
        }
    }

    @Override
    public Map<String, Object> getMenuOptimizationInsights() {
        try {
            return objectMapper.readValue(
                    getAIFile("menu_optimization.json"),
                    new TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("Menu optimization AI file not found", e);
        }
    }

    @Override
    public Map<String, Object> getDishExplanation(String dishName) {
        try {
            return objectMapper.readValue(
                    getAIFile("dish_explanation.json"),
                    new TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("Dish explanation AI file not found", e);
        }
    }
}
