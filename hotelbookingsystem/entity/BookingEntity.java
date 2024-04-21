package com.finalproject.hotelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEntity {
    @Id
    @GeneratedValue
    private Long bookingId;
    @Column(name = "duration")
    private Integer duration;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customerEntity;
    @ManyToOne
    @JoinColumn(name = "hotelId")
    private HotelEntity hotelEntity;
    @ManyToOne
    @JoinColumn(name = "roomId")
    private RoomEntity roomEntity;
}
