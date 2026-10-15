package com.eventhub.enums;

public enum BookingStatus {

    // Customer has submitted an event request
    REQUESTED,

    // Manager accepted the request
    ACCEPTED,

    // Manager fixed an appointment with the customer
    APPOINTMENT_FIXED,

    // Customer and manager completed the meeting
    MEETING_COMPLETED,

    // Manager has prepared the final quotation
    QUOTE_GENERATED,

    // Customer needs to make payment at EventHub office
    PAYMENT_PENDING,

    // Manager has confirmed that payment was received
    PAYMENT_COMPLETED,

    // Event is currently being managed/conducted
    ONGOING,

    // Event has been completed
    COMPLETED,

    // Manager rejected the request
    REJECTED,

    // Customer/manager cancelled the event
    CANCELLED
}