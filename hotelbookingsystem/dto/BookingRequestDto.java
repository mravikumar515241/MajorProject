package com.finalproject.hotelbookingsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {
    @NotNull
    @Min(value = 0,message = "duration can not be negative")
    private Integer duration;
    @NotNull(message = "customer Id cannot be null")
    private Long customerId;
    @NotNull(message = "hotel Id cannot be null")
    private Integer hotelId;
    @NotNull(message = "Room Id cannot be null")
    private Integer roomId;
}
