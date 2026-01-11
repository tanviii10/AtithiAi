package com.atithiai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.atithiai.entities.MenuItem;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
