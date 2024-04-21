
package com.finalproject.hotelbookingsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    @NotEmpty(message = "Room Type can not be empty. Enter room Type")
    private String roomType;
    @NotNull
    @Min(value=0,message = "Hotel Id should be positive")
    private int hotelId;
    private String status="vacant";
}
