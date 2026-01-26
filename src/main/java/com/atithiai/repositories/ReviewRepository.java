package com.atithiai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atithiai.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
