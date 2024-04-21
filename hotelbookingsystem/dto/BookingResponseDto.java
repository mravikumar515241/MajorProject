package com.finalproject.hotelbookingsystem.dto;

import com.finalproject.hotelbookingsystem.entity.CustomerEntity;
import com.finalproject.hotelbookingsystem.entity.HotelEntity;
import com.finalproject.hotelbookingsystem.entity.RoomEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {
    @NotNull(message = "booking Id can not be null")
    private Long bookingId;
    @NotNull(message = "duration can not be null")
    private Integer duration;
    @NotNull
    private CustomerEntity customerEntity;
    @NotNull
    private HotelEntity hotelEntity;
    @NotNull
    private RoomEntity roomEntity;
}
