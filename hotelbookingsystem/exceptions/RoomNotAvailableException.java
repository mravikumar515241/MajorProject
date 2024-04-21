package com.finalproject.hotelbookingsystem.exceptions;

public class RoomNotAvailableException extends RuntimeException{
    public RoomNotAvailableException(String message) {
        super(message);
    }
}
