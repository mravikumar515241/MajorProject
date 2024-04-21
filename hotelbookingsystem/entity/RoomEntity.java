package com.finalproject.hotelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomEntity {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private int roomId;
    private String roomType;
    @JoinColumn(name="hotelId")
    @ManyToOne
    private HotelEntity hotelEntity;
    @Column(name = "status")
    private String status="vacant";
}
