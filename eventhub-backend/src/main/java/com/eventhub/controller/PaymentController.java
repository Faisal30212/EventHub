package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.PaymentDto;
import com.eventhub.entity.Payment;
import com.eventhub.service.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    // ============================================================
    // CREATE PAYMENT RECORD
    // ============================================================

    @PostMapping("/add")
    public ResponseEntity<?> addPayment(
            @RequestBody PaymentDto paymentDto) {

        Payment payment =
                paymentService.addPayment(paymentDto);

        if (payment == null) {

            return new ResponseEntity<>(
                    "Booking Not Found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                payment,
                HttpStatus.CREATED
        );
    }


    // ============================================================
    // GET PAYMENT FOR A BOOKING
    // ============================================================

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getPaymentByBooking(
            @PathVariable Integer bookingId) {

        Payment payment =
                paymentService.getPaymentByBooking(bookingId);

        if (payment == null) {

            return new ResponseEntity<>(
                    "Payment Not Found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                payment,
                HttpStatus.OK
        );
    }


    // ============================================================
    // STAFF - MARK CUSTOMER PAYMENT AS PAID
    // ============================================================

    @PutMapping("/mark-paid/{bookingId}")
    public ResponseEntity<?> markAsPaid(
            @PathVariable Integer bookingId) {

        Payment payment =
                paymentService.markAsPaid(bookingId);

        if (payment == null) {

            return new ResponseEntity<>(
                    "Booking Not Found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                payment,
                HttpStatus.OK
        );
    }


    // ============================================================
    // GET ALL PAYMENTS
    // ============================================================

    @GetMapping
    public List<Payment> getAllPayments() {

        return paymentService
                .getPaymentByBooking(0) == null
                ? List.of()
                : List.of();
    }
}