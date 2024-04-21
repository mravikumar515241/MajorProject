package com.finalproject.hotelbookingsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDto {
        @NotNull
        @NotEmpty(message = "HotelName cannot empty !! enter the Hotel name")
        private String name;
        @NotNull
        @NotEmpty(message = "address cannot be empty . Enter the  address")
        private String address;
    }



