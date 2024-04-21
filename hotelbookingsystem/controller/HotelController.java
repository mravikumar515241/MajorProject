package com.finalproject.hotelbookingsystem.controller;

import com.finalproject.hotelbookingsystem.dto.HotelDto;
import com.finalproject.hotelbookingsystem.service.interfaces.HotelService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel-api/v1")
public class HotelController {
    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
    private final HotelService hotelService;
    @Autowired
    public HotelController(HotelService hotelService) {
        logger.info("hotel controller constructor called");
        this.hotelService = hotelService;
    }
    @GetMapping("/hotels/{id}")
    public HotelDto getHotelById(@PathVariable("id") Integer hotelId) {
        logger.info("Fetching hotel by id: {}", hotelId);
        return hotelService.getHotelById(hotelId);
    }
    @GetMapping("/hotels")
    public List<HotelDto> getAllHotels() {
        logger.info("Fetching all hotels");
        return hotelService.getAllHotels();
    }
    @PostMapping(value = "/hotels")
    public HotelDto createHotel(@RequestBody @Valid HotelDto hotelDto) {
        logger.info("Creating hotel: {}", hotelDto);
        return hotelService.createHotel(hotelDto);
    }
    @PutMapping("/hotels/{id}")
    public HotelDto updateHotelById(@PathVariable("id") Integer hotelId, @RequestBody @Valid HotelDto updatedHotelDto) {
        logger.info("Updating hotel with id {}: {}", hotelId, updatedHotelDto);
        return hotelService.updateHotelById(hotelId, updatedHotelDto);
    }
    @DeleteMapping("/hotels/{id}")
    public void deleteHotel(@PathVariable("id") Integer hotelId) {
        logger.info("Deleting hotel with id: {}", hotelId);
        hotelService.deleteHotel(hotelId);
    }
}
