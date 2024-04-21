package com.finalproject.hotelbookingsystem.repository;

import com.finalproject.hotelbookingsystem.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {
}
