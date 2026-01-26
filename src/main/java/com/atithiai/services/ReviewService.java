package com.atithiai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atithiai.entities.Review;
import com.atithiai.repositories.ReviewRepository;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
