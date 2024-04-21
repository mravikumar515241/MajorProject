
package com.finalproject.hotelbookingsystem.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Roles> roles;
}