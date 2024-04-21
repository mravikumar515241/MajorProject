package com.finalproject.hotelbookingsystem.repository;

import com.finalproject.hotelbookingsystem.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {

}
