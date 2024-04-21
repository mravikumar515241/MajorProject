package com.finalproject.hotelbookingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingUpdateDto {
    @NotNull
    private Integer roomId;
    @NotNull
    private Integer duration;
}
