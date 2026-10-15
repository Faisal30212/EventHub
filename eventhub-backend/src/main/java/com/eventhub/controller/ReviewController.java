package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.ReviewDto;
import com.eventhub.entity.Review;
import com.eventhub.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Add Review
    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody ReviewDto reviewDto) {

        return new ResponseEntity<>(reviewService.addReview(reviewDto), HttpStatus.CREATED);
    }

    // Get All Reviews
    @GetMapping
    public List<Review> getAllReviews() {

        return reviewService.getAllReviews();
    }

    // Get Review By Id
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Integer id) {

        return reviewService.getReviewById(id);
    }

    // Hide Review
    @PutMapping("/hide/{id}")
    public ResponseEntity<String> hideReview(@PathVariable Integer id) {

        return new ResponseEntity<>(reviewService.hideReview(id), HttpStatus.OK);
    }

    // Delete Review
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer id) {

        return new ResponseEntity<>(reviewService.deleteReview(id), HttpStatus.OK);
    }
}