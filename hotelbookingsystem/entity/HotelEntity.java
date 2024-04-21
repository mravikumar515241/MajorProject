package com.finalproject.hotelbookingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelEntity {
    @Id
    @GeneratedValue
    @Column(name = "hotel_id")
    private Integer hotelId;
    private String name;
    private String address;
}
