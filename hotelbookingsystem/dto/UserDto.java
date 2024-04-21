
package com.finalproject.hotelbookingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "username cannot be null")
    private String username;
    @NotNull(message = "password cannot be null")
    private String password;
    @NotNull(message = "mention your role \n 1.CUSTOMER \n 2.ADMIN")
    private String roles;
}
