package com.atithiai.services.ai;

import java.util.List;
import java.util.Map;

public interface AIInsightService {

	Map<String, Object> getPeakHours();

    Map<String, String> getFoodDemand();

    List<String> getMenuOptimizationInsights();

    Map<String, String> getDishExplanation(String dishName);
}
