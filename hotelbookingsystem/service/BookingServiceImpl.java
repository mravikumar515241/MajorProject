package com.finalproject.hotelbookingsystem.service;

import com.finalproject.hotelbookingsystem.dto.BookingRequestDto;
import com.finalproject.hotelbookingsystem.dto.BookingResponseDto;
import com.finalproject.hotelbookingsystem.dto.BookingUpdateDto;
import com.finalproject.hotelbookingsystem.entity.BookingEntity;
import com.finalproject.hotelbookingsystem.entity.CustomerEntity;
import com.finalproject.hotelbookingsystem.entity.HotelEntity;
import com.finalproject.hotelbookingsystem.entity.RoomEntity;
import com.finalproject.hotelbookingsystem.exceptions.*;
import com.finalproject.hotelbookingsystem.repository.BookingRepository;
import com.finalproject.hotelbookingsystem.repository.CustomerRepository;
import com.finalproject.hotelbookingsystem.repository.HotelRepository;
import com.finalproject.hotelbookingsystem.repository.RoomRepository;
import com.finalproject.hotelbookingsystem.service.interfaces.BookingService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepo;
    private final RoomRepository roomRepo;
    private final CustomerRepository customerRepo;
    private final HotelRepository hotelRepo;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    private static final String STATUS_BOOKED = "Booked";
    private static final String STATUS_VACANT = "Vacant";

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepo, RoomRepository roomRepo, CustomerRepository customerRepo, HotelRepository hotelRepo, ModelMapper modelMapper) {
        logger.info("Booking Service Layer called");
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
        this.customerRepo = customerRepo;
        this.hotelRepo = hotelRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public BookingResponseDto updateBooking(Long bookingId, BookingUpdateDto bookingUpdateDto) {
        logger.info("updateBooking called");
        Optional<BookingEntity> optionalBookingEntity = bookingRepo.findById(bookingId);
        if (optionalBookingEntity.isPresent()) {
            logger.info("valid Booking Id");
            BookingEntity bookingEntity = optionalBookingEntity.get();
            Integer duration = bookingUpdateDto.getDuration();
            if(duration != null) {
                logger.info("Customer wants to change duration");
                bookingEntity.setDuration(bookingUpdateDto.getDuration());
            }
            Optional<RoomEntity> optionalRoomEntity = roomRepo.findById(bookingUpdateDto.getRoomId());
            if(optionalRoomEntity.isPresent()){
                logger.info("New Room ID is valid");
                RoomEntity oldRoomEntity = bookingEntity.getRoomEntity();
                RoomEntity newRoomEntity = optionalRoomEntity.get();
                if(oldRoomEntity.getRoomId() == newRoomEntity.getRoomId()){
                    logger.info("Customer is not changing the room");
                    return modelMapper.map(bookingRepo.save(bookingEntity), BookingResponseDto.class);
                }
                if(Objects.equals(newRoomEntity.getStatus(), STATUS_BOOKED)){
                    throw new RoomNotAvailableException("Room not available");
                }
                oldRoomEntity.setStatus(STATUS_VACANT);
                newRoomEntity.setStatus(STATUS_BOOKED);
                bookingEntity.setRoomEntity(newRoomEntity);
                roomRepo.save(oldRoomEntity);
                roomRepo.save(newRoomEntity);
                logger.info("Exiting updateBooking");
                return modelMapper.map(bookingRepo.save(bookingEntity), BookingResponseDto.class);
            }
            else {
                throw new RoomIdNotFoundException("New Room ID not found");
            }

        } else {
            throw new BookingIdDoesNotExistException("Booking ID does not exist");
        }
    }
    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        logger.info("createBooking Called");
        Optional<CustomerEntity> optionalCustomerEntity = customerRepo.findById(bookingRequestDto.getCustomerId());
        Optional<HotelEntity> optionalHotelEntity = hotelRepo.findById(bookingRequestDto.getHotelId());
        Optional<RoomEntity> optionalRoomEntity = roomRepo.findById(bookingRequestDto.getRoomId());
        if(optionalCustomerEntity.isEmpty()){
            throw new CustomerIdDoesNotExistException("Customer ID does not exist");
        } else if (optionalHotelEntity.isEmpty()) {
            throw new HotelIdNotFoundException("Hotel ID does not exist");
        } else if (optionalRoomEntity.isEmpty()) {
            throw new RoomIdNotFoundException("Room ID does not exist");
        }
        logger.info("All IDs are valid");
        RoomEntity currentRoom = optionalRoomEntity.get();
        if(Objects.equals(currentRoom.getStatus(), STATUS_BOOKED)){
            throw new RoomNotAvailableException("Room not available");
        }
        logger.info("selected room is available");
        BookingEntity bookingEntity = BookingEntity.builder()
                .duration(bookingRequestDto.getDuration())
                .customerEntity(optionalCustomerEntity.get())
                .hotelEntity(optionalHotelEntity.get())
                .roomEntity(currentRoom)
                .build();
        currentRoom.setStatus(STATUS_BOOKED);
        roomRepo.save(currentRoom);
        logger.info("Exiting createBooking");
        return modelMapper.map(bookingRepo.save(bookingEntity), BookingResponseDto.class);
    }
    @Override
    public void cancelBooking(Long bookingId) {
        logger.info("cancelBooking called");
        Optional<BookingEntity> optionalBookingEntity = bookingRepo.findById(bookingId);
        if (optionalBookingEntity.isPresent()) {
            logger.info("valid booking ID");
            BookingEntity bookingEntity = optionalBookingEntity.get();
            RoomEntity oldRoomEntity = bookingEntity.getRoomEntity();
            if (oldRoomEntity != null) {
                logger.info("setting the room status to vacant");
                oldRoomEntity.setStatus(STATUS_VACANT);
                roomRepo.save(oldRoomEntity);
            }
            bookingRepo.deleteById(bookingId);
            logger.info("booking deleted");
        } else {
            throw new BookingIdDoesNotExistException("Booking ID does not exist");
        }
    }
    @Override
    public BookingResponseDto getBookingById(Long bookingId) {
        logger.info("getBookingId called");
        Optional<BookingEntity> bookingEntity = bookingRepo.findById(bookingId);
        if(bookingEntity.isEmpty()){
            throw new BookingIdDoesNotExistException("Booking ID not found");
        }
        return modelMapper.map(bookingEntity.get(), BookingResponseDto.class);
    }
    @Override
    public List<BookingResponseDto> getAllBookings(){
        logger.info("getAllBookings called");
        return bookingRepo.findAll()
                .stream()
                .map(e -> modelMapper.map(e, BookingResponseDto.class))
                .toList();
    }
}
