package com.eventhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.DashboardDto;
import com.eventhub.repository.BookingRepository;
import com.eventhub.repository.EventRepository;
import com.eventhub.repository.ReviewRepository;
import com.eventhub.repository.UserRepository;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // Get Dashboard Details
    public DashboardDto getDashboardDetails() {

        DashboardDto dashboard = new DashboardDto();

        dashboard.setTotalUsers(userRepository.count());

        dashboard.setTotalEvents(eventRepository.count());

        dashboard.setTotalBookings(bookingRepository.count());

        dashboard.setTotalReviews(reviewRepository.count());

        Double totalRevenue = bookingRepository.findAll()
                .stream()
                .mapToDouble(booking -> booking.getTotalAmount())
                .sum();

        dashboard.setTotalRevenue(totalRevenue);

        return dashboard;
    }

}