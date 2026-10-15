package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.BookingDto;
import com.eventhub.dto.BookingManagementDto;
import com.eventhub.entity.Booking;
import com.eventhub.service.BookingService;

@RestController
@RequestMapping("/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    // ============================================================
    // CUSTOMER - SUBMIT EVENT REQUEST
    // ============================================================

    @PostMapping("/add")
    public ResponseEntity<?> addBooking(
            @RequestBody BookingDto bookingDto) {

        Booking booking =
                bookingService.addBooking(bookingDto);

        if (booking == null) {

            return new ResponseEntity<>(
                    "Invalid event request. Please check customer, event, venue, date and guest count.",
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                booking,
                HttpStatus.CREATED
        );
    }


    // ============================================================
    // STAFF - VIEW ALL EVENT REQUESTS
    // ============================================================

    @GetMapping
    public List<Booking> getAllBookings() {

        return bookingService.getAllBookings();
    }


    // ============================================================
    // GET ONE EVENT REQUEST
    // ============================================================

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(
            @PathVariable Integer id) {

        Booking booking =
                bookingService.getBookingById(id);

        if (booking == null) {

            return new ResponseEntity<>(
                    "Event Request Not Found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                booking,
                HttpStatus.OK
        );
    }


    // ============================================================
    // CUSTOMER - VIEW THEIR EVENTS
    // ============================================================

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(
            @PathVariable Integer userId) {

        return bookingService.getBookingsByUser(userId);
    }


    // ============================================================
    // STAFF / MANAGER - MANAGE EVENT
    // ============================================================

    @PutMapping("/manage/{id}")
    public ResponseEntity<?> manageBooking(
            @PathVariable Integer id,
            @RequestBody BookingManagementDto dto) {

        Booking booking =
                bookingService.manageBooking(id, dto);

        if (booking == null) {

            return new ResponseEntity<>(
                    "Unable to update event request. Please check the provided details.",
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                booking,
                HttpStatus.OK
        );
    }


    // ============================================================
    // CUSTOMER - CANCEL EVENT
    // ============================================================

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(
            @PathVariable Integer id) {

        return new ResponseEntity<>(
                bookingService.cancelBooking(id),
                HttpStatus.OK
        );
    }


    // ============================================================
    // DELETE EVENT REQUEST
    // ============================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Integer id) {

        return new ResponseEntity<>(
                bookingService.deleteBooking(id),
                HttpStatus.OK
        );
    }
}