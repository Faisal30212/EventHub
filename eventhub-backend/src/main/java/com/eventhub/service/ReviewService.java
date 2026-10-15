package com.eventhub.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.ReviewDto;
import com.eventhub.entity.Event;
import com.eventhub.entity.Review;
import com.eventhub.entity.User;
import com.eventhub.enums.ReviewStatus;
import com.eventhub.repository.EventRepository;
import com.eventhub.repository.ReviewRepository;
import com.eventhub.repository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    // Add Review
    public String addReview(ReviewDto reviewDto) {

        Optional<User> userOptional = userRepository.findById(reviewDto.getUserId());

        if (userOptional.isEmpty()) {
            return "User Not Found";
        }

        Optional<Event> eventOptional = eventRepository.findById(reviewDto.getEventId());

        if (eventOptional.isEmpty()) {
            return "Event Not Found";
        }

        Review review = new Review();

        review.setUser(userOptional.get());
        review.setEvent(eventOptional.get());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setReviewDate(LocalDate.now());
        review.setReviewStatus(ReviewStatus.ACTIVE);

        reviewRepository.save(review);

        return "Review Added Successfully";
    }

    // Get All Reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get Review By Id
    public Review getReviewById(Integer id) {

        Optional<Review> optional = reviewRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    // Hide Review
    public String hideReview(Integer id) {

        Optional<Review> optional = reviewRepository.findById(id);

        if (optional.isPresent()) {

            Review review = optional.get();

            review.setReviewStatus(ReviewStatus.HIDDEN);

            reviewRepository.save(review);

            return "Review Hidden Successfully";
        }

        return "Review Not Found";
    }

    // Delete Review
    public String deleteReview(Integer id) {

        Optional<Review> optional = reviewRepository.findById(id);

        if (optional.isPresent()) {

            reviewRepository.deleteById(id);

            return "Review Deleted Successfully";
        }

        return "Review Not Found";
    }
}