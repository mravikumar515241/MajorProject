package com.finalproject.hotelbookingsystem.service;

import com.finalproject.hotelbookingsystem.dto.HotelDto;
import com.finalproject.hotelbookingsystem.entity.HotelEntity;
import com.finalproject.hotelbookingsystem.exceptions.HotelIdNotFoundException;
import com.finalproject.hotelbookingsystem.repository.HotelRepository;
import com.finalproject.hotelbookingsystem.service.interfaces.HotelService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, ModelMapper modelMapper) {
        logger.info("HotelServiceImpl constructor called");
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public HotelDto getHotelById(Integer hotelId) {
        logger.info("Fetching hotel by id: {}", hotelId);
        Optional<HotelEntity> hotelOptional = hotelRepository.findById(hotelId);
        if(hotelOptional.isEmpty()){
            logger.warn("Hotel not found with id: {}", hotelId);
            throw new HotelIdNotFoundException("Hotel Id not found");
        }
        return modelMapper.map(hotelOptional.get(), HotelDto.class);
    }
    @Override
    public List<HotelDto> getAllHotels() {
        logger.info("Fetching all hotels");
        return hotelRepository.findAll()
                .stream()
                .map(hotelEntity -> modelMapper.map(hotelEntity, HotelDto.class))
                .toList();
    }
    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        logger.info("Creating hotel: {}", hotelDto);
        HotelEntity savedHotelEntity = hotelRepository.save(modelMapper.map(hotelDto, HotelEntity.class));
        return modelMapper.map(savedHotelEntity, HotelDto.class);
    }
    @Override
    public HotelDto updateHotelById(Integer hotelId, HotelDto updatedHotelDto) {
        logger.info("Creating hotel: {}", updatedHotelDto);
        Optional<HotelEntity> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isEmpty()) {
            logger.warn("Hotel not found with id: {}", hotelId);
            throw new HotelIdNotFoundException("Hotel not found with id: " + hotelId);
        }

        HotelEntity existingHotel = optionalHotel.get();
        existingHotel.setName(updatedHotelDto.getName());
        existingHotel.setAddress(updatedHotelDto.getAddress());

        HotelEntity updatedHotelEntity = hotelRepository.save(existingHotel);
        return modelMapper.map(updatedHotelEntity, HotelDto.class);
    }
    @Override
    public void deleteHotel(Integer hotelId) {
        logger.info("Deleting hotel with id: {}", hotelId);
        if (hotelRepository.existsById(hotelId)) {
            hotelRepository.deleteById(hotelId);
            logger.info("Hotel deleted successfully");
        } else {
            throw new HotelIdNotFoundException("Hotel ID not found");
        }
    }
}
