package com.finalproject.hotelbookingsystem.service;

import com.finalproject.hotelbookingsystem.dto.RoomDto;
import com.finalproject.hotelbookingsystem.dto.RoomResponseDto;
import com.finalproject.hotelbookingsystem.entity.HotelEntity;
import com.finalproject.hotelbookingsystem.entity.RoomEntity;
import com.finalproject.hotelbookingsystem.exceptions.HotelIdNotFoundException;
import com.finalproject.hotelbookingsystem.exceptions.RoomIdNotFoundException;
import com.finalproject.hotelbookingsystem.repository.HotelRepository;
import com.finalproject.hotelbookingsystem.repository.RoomRepository;
import com.finalproject.hotelbookingsystem.service.interfaces.RoomService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private static final Logger logger= LoggerFactory.getLogger(RoomServiceImpl.class);
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelmapper;
    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository, ModelMapper modelmapper) {
        logger.info("Room service implementation constructor is called");
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.modelmapper = modelmapper;
    }
    @Override
    public RoomResponseDto createRoom(RoomDto roomDto) {
        Optional<HotelEntity> optionalHotelEntity = hotelRepository.findById(roomDto.getHotelId());
        if(optionalHotelEntity.isEmpty()){
            throw new HotelIdNotFoundException("Hotel Id not found");
        }
        RoomEntity roomEntity = RoomEntity.builder()
                .roomType(roomDto.getRoomType())
                .hotelEntity(optionalHotelEntity.get())
                .status(roomDto.getStatus()==null?"Vacant":roomDto.getStatus())
                .build();
        logger.info("Room is created successfully");
        return modelmapper.map(roomRepository.save(roomEntity), RoomResponseDto.class);
    }
    @Override
    public RoomResponseDto getRoomById(int roomId) {
        Optional<RoomEntity> roomEntityOptional= roomRepository.findById(roomId);
        if(roomEntityOptional.isEmpty()) {
            logger.warn("Room ID does not found");
            throw new RoomIdNotFoundException("Room Id is not found");
        }
        return modelmapper.map(roomEntityOptional.get(),RoomResponseDto.class);
    }
    @Override
    public List<RoomResponseDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(e->modelmapper.map(e,RoomResponseDto.class))
                .toList();
    }
    @Override
    public void deleteRoomById(Integer roomId) {
        if(roomRepository.existsById(roomId)) {
            roomRepository.deleteById(roomId);
            logger.info("Room is deleted successfully");
        }
        else{
        logger.warn("Room ID does not exist");
        throw new RoomIdNotFoundException("Room Id is not found");
        }
    }
    @Override
    public RoomResponseDto updateRoomById(int roomId,RoomDto roomDto) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            logger.warn("Room not found");
            throw new RoomIdNotFoundException("Room not found");
        }

        RoomEntity roomEntity = optionalRoom.get();
        roomEntity.setRoomType(roomDto.getRoomType());
        roomEntity.setStatus(roomDto.getStatus()==null?"Vacant":roomDto.getStatus());

        return modelmapper.map(roomRepository.save(roomEntity), RoomResponseDto.class);
    }
}





