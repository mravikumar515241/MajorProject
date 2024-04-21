package com.finalproject.hotelbookingsystem.service.interfaces;

import com.finalproject.hotelbookingsystem.dto.RoomDto;
import com.finalproject.hotelbookingsystem.dto.RoomResponseDto;

import java.util.List;

public interface RoomService {

    RoomResponseDto createRoom(RoomDto roomDto);
    RoomResponseDto getRoomById(int roomId);
    List<RoomResponseDto> getAllRooms();
    void deleteRoomById(Integer roomId);
    RoomResponseDto updateRoomById(int roomId,RoomDto roomDto );

}

