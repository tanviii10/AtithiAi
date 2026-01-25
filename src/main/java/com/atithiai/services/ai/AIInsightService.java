package com.atithiai.services.ai;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AIInsightService {

	Map<String, Object> getPeakHours();

    Map<String, String> getFoodDemand();

    Map<String, Object> getMenuOptimizationInsights();

    Map<String, Object> getDishExplanation(String dishName);
    Set<String> getAllDishNames();
}
