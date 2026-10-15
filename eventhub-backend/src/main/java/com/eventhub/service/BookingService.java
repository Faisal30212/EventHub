package com.eventhub.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.BookingDto;
import com.eventhub.dto.BookingManagementDto;
import com.eventhub.entity.Booking;
import com.eventhub.entity.Event;
import com.eventhub.entity.User;
import com.eventhub.entity.Venue;
import com.eventhub.enums.BookingStatus;
import com.eventhub.repository.BookingRepository;
import com.eventhub.repository.EventRepository;
import com.eventhub.repository.UserRepository;
import com.eventhub.repository.VenueRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VenueRepository venueRepository;


    // ============================================================
    // CUSTOMER - CREATE EVENT REQUEST
    // ============================================================

    public Booking addBooking(BookingDto bookingDto) {

        Optional<User> userOptional =
                userRepository.findById(bookingDto.getUserId());

        if (userOptional.isEmpty()) {
            return null;
        }

        Optional<Event> eventOptional =
                eventRepository.findById(bookingDto.getEventId());

        if (eventOptional.isEmpty()) {
            return null;
        }

        Optional<Venue> venueOptional =
                venueRepository.findById(bookingDto.getVenueId());

        if (venueOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        Event event = eventOptional.get();
        Venue venue = venueOptional.get();

        if (bookingDto.getGuestCount() == null ||
                bookingDto.getGuestCount() < 1) {

            return null;
        }

        if (venue.getCapacity() != null &&
                bookingDto.getGuestCount() > venue.getCapacity()) {

            return null;
        }

        if (bookingDto.getEventDate() == null) {
            return null;
        }

        if (bookingDto.getEventDate().isBefore(LocalDate.now())) {
            return null;
        }

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setEvent(event);
        booking.setVenue(venue);

        booking.setBookingDate(LocalDate.now());

        booking.setEventDate(bookingDto.getEventDate());
        booking.setEventTime(bookingDto.getEventTime());
        booking.setGuestCount(bookingDto.getGuestCount());
        booking.setRequirements(bookingDto.getRequirements());

        // Initial estimate = event package + venue
        Double eventPackagePrice =
                event.getBasePrice() == null
                        ? 0.0
                        : event.getBasePrice();

        Double venuePrice =
                venue.getPricePerDay() == null
                        ? 0.0
                        : venue.getPricePerDay();

        booking.setTotalAmount(eventPackagePrice + venuePrice);

        // Request waits for manager
        booking.setBookingStatus(BookingStatus.REQUESTED);

        return bookingRepository.save(booking);
    }


    // ============================================================
    // GET ALL EVENT REQUESTS
    // ============================================================

    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }


    // ============================================================
    // GET ONE EVENT REQUEST
    // ============================================================

    public Booking getBookingById(Integer id) {

        Optional<Booking> optional =
                bookingRepository.findById(id);

        return optional.orElse(null);
    }


    // ============================================================
    // GET CUSTOMER EVENTS
    // ============================================================

    public List<Booking> getBookingsByUser(Integer userId) {

        return bookingRepository.findByUserUserId(userId);
    }


    // ============================================================
    // STAFF / MANAGER - UPDATE EVENT
    // ============================================================

    public Booking manageBooking(
            Integer bookingId,
            BookingManagementDto dto) {

        Optional<Booking> bookingOptional =
                bookingRepository.findById(bookingId);

        if (bookingOptional.isEmpty()) {
            return null;
        }

        Booking booking = bookingOptional.get();


        // ------------------------------------------------------------
        // Update Event Type
        // ------------------------------------------------------------

        if (dto.getEventId() != null) {

            Optional<Event> eventOptional =
                    eventRepository.findById(dto.getEventId());

            if (eventOptional.isEmpty()) {
                return null;
            }

            booking.setEvent(eventOptional.get());
        }


        // ------------------------------------------------------------
        // Update Venue
        // ------------------------------------------------------------

        if (dto.getVenueId() != null) {

            Optional<Venue> venueOptional =
                    venueRepository.findById(dto.getVenueId());

            if (venueOptional.isEmpty()) {
                return null;
            }

            Venue venue = venueOptional.get();

            // Check guest capacity
            if (dto.getGuestCount() != null &&
                    venue.getCapacity() != null &&
                    dto.getGuestCount() > venue.getCapacity()) {

                return null;
            }

            booking.setVenue(venue);
        }


        // ------------------------------------------------------------
        // Update Event Date
        // ------------------------------------------------------------

        if (dto.getEventDate() != null) {

            booking.setEventDate(dto.getEventDate());
        }


        // ------------------------------------------------------------
        // Update Event Time
        // ------------------------------------------------------------

        if (dto.getEventTime() != null) {

            booking.setEventTime(dto.getEventTime());
        }


        // ------------------------------------------------------------
        // Update Guest Count
        // ------------------------------------------------------------

        if (dto.getGuestCount() != null) {

            if (dto.getGuestCount() < 1) {
                return null;
            }

            booking.setGuestCount(dto.getGuestCount());
        }


        // ------------------------------------------------------------
        // Update Requirements
        // ------------------------------------------------------------

        if (dto.getRequirements() != null) {

            booking.setRequirements(dto.getRequirements());
        }


        // ------------------------------------------------------------
        // Appointment
        // ------------------------------------------------------------

        if (dto.getAppointmentDate() != null) {

            booking.setAppointmentDate(
                    dto.getAppointmentDate()
            );
        }

        if (dto.getAppointmentTime() != null) {

            booking.setAppointmentTime(
                    dto.getAppointmentTime()
            );
        }


        // ------------------------------------------------------------
        // Meeting Notes
        // ------------------------------------------------------------

        if (dto.getMeetingNotes() != null) {

            booking.setMeetingNotes(
                    dto.getMeetingNotes()
            );
        }


        // ------------------------------------------------------------
        // Final Quotation
        // ------------------------------------------------------------

        if (dto.getQuoteAmount() != null) {

            if (dto.getQuoteAmount() < 0) {
                return null;
            }

            booking.setQuoteAmount(
                    dto.getQuoteAmount()
            );
        }

        if (dto.getQuoteDetails() != null) {

            booking.setQuoteDetails(
                    dto.getQuoteDetails()
            );
        }


        // ------------------------------------------------------------
        // Status
        // ------------------------------------------------------------

        if (dto.getBookingStatus() != null) {

            booking.setBookingStatus(
                    dto.getBookingStatus()
            );
        }


        return bookingRepository.save(booking);
    }


    // ============================================================
    // CANCEL EVENT
    // ============================================================

    public String cancelBooking(Integer id) {

        Optional<Booking> optional =
                bookingRepository.findById(id);

        if (optional.isPresent()) {

            Booking booking = optional.get();

            booking.setBookingStatus(
                    BookingStatus.CANCELLED
            );

            bookingRepository.save(booking);

            return "Event Request Cancelled Successfully";
        }

        return "Event Request Not Found";
    }


    // ============================================================
    // DELETE EVENT REQUEST
    // ============================================================

    public String deleteBooking(Integer id) {

        if (bookingRepository.existsById(id)) {

            bookingRepository.deleteById(id);

            return "Event Request Deleted Successfully";
        }

        return "Event Request Not Found";
    }
}