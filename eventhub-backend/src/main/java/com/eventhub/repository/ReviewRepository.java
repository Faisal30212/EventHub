package com.eventhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhub.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}