package com.finalproject.hotelbookingsystem.exceptions;

public class BookingIdDoesNotExistException extends RuntimeException{
    public BookingIdDoesNotExistException(String s) {
        super(s);
    }
}
