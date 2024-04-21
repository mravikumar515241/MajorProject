package com.finalproject.hotelbookingsystem.service.interfaces;

import com.finalproject.hotelbookingsystem.dto.BookingRequestDto;
import com.finalproject.hotelbookingsystem.dto.BookingResponseDto;
import com.finalproject.hotelbookingsystem.dto.BookingUpdateDto;

import java.util.List;


public interface BookingService {
    BookingResponseDto updateBooking(Long bookingId, BookingUpdateDto booking);
    BookingResponseDto createBooking(BookingRequestDto booking);
    void cancelBooking(Long bookingId);
    BookingResponseDto getBookingById(Long bookingId);
    List<BookingResponseDto> getAllBookings();
}
