package com.finalproject.hotelbookingsystem.controller;

import com.finalproject.hotelbookingsystem.dto.BookingRequestDto;
import com.finalproject.hotelbookingsystem.dto.BookingResponseDto;
import com.finalproject.hotelbookingsystem.dto.BookingUpdateDto;
import com.finalproject.hotelbookingsystem.service.interfaces.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-api/v1")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/bookings")
    public BookingResponseDto createBooking(@RequestBody @Valid BookingRequestDto booking) {
        return bookingService.createBooking(booking);
    }
    @GetMapping("/bookings/{bookingId}")
    public BookingResponseDto getBookingById(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }
    @GetMapping("/bookings")
    public List<BookingResponseDto> getAllBookings(){
        return bookingService.getAllBookings();
    }
    @PutMapping("/bookings/{bookingId}")
    public BookingResponseDto updateBooking(@PathVariable Long bookingId, @RequestBody @Valid BookingUpdateDto booking) {
        return bookingService.updateBooking(bookingId, booking);
    }
    @DeleteMapping("/bookings/{bookingId}")
    public void cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
