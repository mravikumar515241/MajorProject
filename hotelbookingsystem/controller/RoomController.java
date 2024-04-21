package com.finalproject.hotelbookingsystem.controller;


import com.finalproject.hotelbookingsystem.dto.RoomDto;
import com.finalproject.hotelbookingsystem.dto.RoomResponseDto;
import com.finalproject.hotelbookingsystem.service.interfaces.RoomService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-api/v1")
public class RoomController {
    private final RoomService roomService;
    private static final Logger logger= LoggerFactory.getLogger(RoomController.class);
    @Autowired
    public RoomController(RoomService roomService) {
        logger.info("Room Controller constructor called");
        this.roomService = roomService;
    }
    @GetMapping("/rooms/{id}")
    public RoomResponseDto getRoomById(@PathVariable("id") int roomId) {
        logger.info("Fetching room by Id called");
        return roomService.getRoomById(roomId);
    }
    @GetMapping("/rooms")
    public List<RoomResponseDto> getAllRooms() {
        logger.info("fetch all rooms called");
        return roomService.getAllRooms();
    }
    @PostMapping("/rooms")
    public RoomResponseDto createRoom(@RequestBody @Valid RoomDto roomDto) {
        logger.info("create room method is called");
        return roomService.createRoom(roomDto);
    }
    @PutMapping("/rooms/{id}")
    public RoomResponseDto updateRoomById(@PathVariable("id") int roomId, @RequestBody @Valid RoomDto roomDto) {
        logger.info("Update room by ID is called");
        return roomService.updateRoomById(roomId,roomDto);
    }
    @DeleteMapping("/rooms/{id}")
    public void deleteRoomById(@PathVariable("id") Integer roomId) {
        logger.info("delete room by Id is called");
        roomService.deleteRoomById(roomId);
    }
}
