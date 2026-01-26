package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.atithiai.entities.Review;
import com.atithiai.services.ReviewService;

@Controller
public class ReviewController {

	private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Show review page
    @GetMapping("/review")
    public String showReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "customer/review";
    }

    // Submit review
    @PostMapping("/review/submit")
    public String submitReview(@ModelAttribute Review review) {
        reviewService.saveReview(review);
        return "redirect:/index?reviewSuccess";
    }
}
