package com.eventhub.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.eventhub.enums.BookingStatus;

public class BookingManagementDto {

    // Manager can update the event type if required
    private Integer eventId;

    // Manager can change the selected venue
    private Integer venueId;

    // Customer's event date
    private LocalDate eventDate;

    // Customer's event time
    private LocalTime eventTime;

    // Expected number of guests
    private Integer guestCount;

    // Additional customer requirements
    private String requirements;

    // Appointment fixed by manager
    private LocalDate appointmentDate;

    // Appointment time fixed by manager
    private LocalTime appointmentTime;

    // Notes entered after customer meeting
    private String meetingNotes;

    // Final quotation amount
    private Double quoteAmount;

    // Quotation breakdown/details
    private String quoteDetails;

    // Current event status
    private BookingStatus bookingStatus;

    public BookingManagementDto() {
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public Integer getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(Integer guestCount) {
        this.guestCount = guestCount;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getMeetingNotes() {
        return meetingNotes;
    }

    public void setMeetingNotes(String meetingNotes) {
        this.meetingNotes = meetingNotes;
    }

    public Double getQuoteAmount() {
        return quoteAmount;
    }

    public void setQuoteAmount(Double quoteAmount) {
        this.quoteAmount = quoteAmount;
    }

    public String getQuoteDetails() {
        return quoteDetails;
    }

    public void setQuoteDetails(String quoteDetails) {
        this.quoteDetails = quoteDetails;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}