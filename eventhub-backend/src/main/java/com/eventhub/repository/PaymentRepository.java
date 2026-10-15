package com.eventhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhub.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // Find payment belonging to an event request
    Optional<Payment> findByBookingBookingId(Integer bookingId);
}