package com.eventhub.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.PaymentDto;
import com.eventhub.entity.Booking;
import com.eventhub.entity.Payment;
import com.eventhub.enums.BookingStatus;
import com.eventhub.repository.BookingRepository;
import com.eventhub.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;


    // ============================================================
    // CREATE PAYMENT RECORD
    // ============================================================

    public Payment addPayment(PaymentDto paymentDto) {

        Optional<Booking> bookingOptional =
                bookingRepository.findById(paymentDto.getBookingId());

        if (bookingOptional.isEmpty()) {
            return null;
        }

        Booking booking = bookingOptional.get();

        Payment payment = new Payment();

        payment.setBooking(booking);

        payment.setPaymentDate(
                paymentDto.getPaymentDate() == null
                        ? LocalDate.now()
                        : paymentDto.getPaymentDate()
        );

        payment.setPaymentMethod(
                paymentDto.getPaymentMethod() == null
                        ? "OFFICE"
                        : paymentDto.getPaymentMethod()
        );

        payment.setPaymentStatus(
                paymentDto.getPaymentStatus() == null
                        ? "PENDING"
                        : paymentDto.getPaymentStatus()
        );

        payment.setTotalAmount(
                paymentDto.getTotalAmount() == null
                        ? booking.getQuoteAmount()
                        : paymentDto.getTotalAmount()
        );

        return paymentRepository.save(payment);
    }


    // ============================================================
    // GET PAYMENT BY BOOKING
    // ============================================================

    public Payment getPaymentByBooking(Integer bookingId) {

        Optional<Payment> paymentOptional =
                paymentRepository.findByBookingBookingId(bookingId);

        return paymentOptional.orElse(null);
    }


    // ============================================================
    // STAFF - MARK PAYMENT AS PAID
    // ============================================================

    public Payment markAsPaid(Integer bookingId) {

        Optional<Booking> bookingOptional =
                bookingRepository.findById(bookingId);

        if (bookingOptional.isEmpty()) {
            return null;
        }

        Booking booking = bookingOptional.get();

        Payment payment;

        Optional<Payment> paymentOptional =
                paymentRepository.findByBookingBookingId(bookingId);

        if (paymentOptional.isPresent()) {

            payment = paymentOptional.get();

        } else {

            payment = new Payment();
            payment.setBooking(booking);
        }

        // Final quotation becomes the amount to be paid
        Double finalAmount = booking.getQuoteAmount();

        if (finalAmount == null) {
            finalAmount = booking.getTotalAmount();
        }

        payment.setTotalAmount(finalAmount);

        payment.setPaymentDate(LocalDate.now());

        payment.setPaymentMethod("OFFICE");

        payment.setPaymentStatus("PAID");

        Payment savedPayment =
                paymentRepository.save(payment);


        // --------------------------------------------------------
        // Generate bill information automatically
        // --------------------------------------------------------

        booking.setBookingStatus(
                BookingStatus.PAYMENT_COMPLETED
        );

        // Generate a simple unique bill number
        String billNumber =
                "EH-BILL-" + booking.getBookingId();

        booking.setBillNumber(billNumber);

        booking.setBillDate(LocalDate.now());

        bookingRepository.save(booking);

        return savedPayment;
    }
}