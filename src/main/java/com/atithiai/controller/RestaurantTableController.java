package com.atithiai.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.RestaurantTable;
import com.atithiai.services.RestaurantTableService;


@RestController
@RequestMapping("/api/table")
public class RestaurantTableController {

	private final RestaurantTableService restaurantTableService;

    public RestaurantTableController(RestaurantTableService restaurantTableService) {
        this.restaurantTableService = restaurantTableService;
    }

    @PostMapping
    public ResponseEntity<RestaurantTable> addTable(@RequestBody RestaurantTable table) {
        return ResponseEntity.ok(restaurantTableService.addTable(table));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTable>> getAllTables() {
        return ResponseEntity.ok(restaurantTableService.getAllTables());
    }
}
