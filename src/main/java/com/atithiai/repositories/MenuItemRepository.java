package com.atithiai.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.atithiai.entities.MenuItem;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
	
	List<MenuItem> findByCategoryIgnoreCaseAndAvailableTrue(String category);
}
