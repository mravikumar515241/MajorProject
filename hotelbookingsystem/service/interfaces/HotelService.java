package com.finalproject.hotelbookingsystem.service.interfaces;

import com.finalproject.hotelbookingsystem.dto.HotelDto;

import java.util.List;

public interface HotelService {

    HotelDto getHotelById(Integer id);

    List<HotelDto> getAllHotels();

    HotelDto createHotel(HotelDto hotelDto);

    HotelDto updateHotelById(Integer id,HotelDto hotelDto);

    void deleteHotel(Integer id);


}
