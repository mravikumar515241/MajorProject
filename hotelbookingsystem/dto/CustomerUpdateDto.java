package com.finalproject.hotelbookingsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDto {
    @NotNull(message = "first name cannot be null")
    private String firstName;
    @NotNull(message = "last name cannot be null")
    private String lastName;
    @NotNull(message = "email cannot be null")
    @Email(message = "enter valid email")
    private String email;
}
