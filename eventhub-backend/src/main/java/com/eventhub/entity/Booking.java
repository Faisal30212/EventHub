package com.eventhub.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.eventhub.enums.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    // Customer who requested the event
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Event type selected by the customer
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // Venue selected by the customer
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    // Date on which customer submitted the request
    private LocalDate bookingDate;

    // Customer's selected event date
    private LocalDate eventDate;

    // Customer's selected event time
    private LocalTime eventTime;

    // Expected number of guests
    @Column(name = "guest_count")
    private Integer guestCount;

    // Customer's additional requirements
    @Column(length = 1000)
    private String requirements;

    // Initial estimated amount
    private Double totalAmount;

    // Appointment fixed by manager
    private LocalDate appointmentDate;

    // Appointment time fixed by manager
    private LocalTime appointmentTime;

    // Notes entered by manager after meeting
    @Column(length = 2000)
    private String meetingNotes;

    // Final quotation amount
    private Double quoteAmount;

    // Details of the quotation
    @Column(length = 2000)
    private String quoteDetails;

    // Bill number generated after payment
    private String billNumber;

    // Date on which bill was generated
    private LocalDate billDate;

    // Current event-management status
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    public Booking() {
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}