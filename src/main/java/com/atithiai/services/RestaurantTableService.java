package com.atithiai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atithiai.entities.RestaurantTable;
import com.atithiai.repositories.RestaurantTableRepository;

@Service
public class RestaurantTableService {
	
	private final RestaurantTableRepository restaurantTableRepository;

    public RestaurantTableService(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    public RestaurantTable addTable(RestaurantTable table) {
        return restaurantTableRepository.save(table);
    }

    public List<RestaurantTable> getAllTables() {
        return restaurantTableRepository.findAll();
    }

    public RestaurantTable updateTable(RestaurantTable table) {
        return restaurantTableRepository.save(table);
    }
}
