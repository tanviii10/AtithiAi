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


	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final String PEAK_HOURS_FILE =
            "atithiai-ai-module/ai_output/peak_hours.json";

    private static final String MENU_OPTIMIZATION_PATH =
            "ai_output/menu_optimization.json";
    

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
    public Map<String, Object> getMenuOptimizationInsights() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(
                    new File(MENU_OPTIMIZATION_PATH),
                    new TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Menu Optimization Insights", e);
        }
    }
    
    //Dish Explanation
    @Override
    public Map<String, Object> getDishExplanation(String dishName) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            File file = new File("ai_output/dish_explanation.json");

            Map<String, Object> data =
                    mapper.readValue(file, Map.class);

            return data;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read dish explanation JSON", e);
        }
    }

}
