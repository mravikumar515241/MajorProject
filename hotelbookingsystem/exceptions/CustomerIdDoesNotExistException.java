package com.finalproject.hotelbookingsystem.exceptions;

public class CustomerIdDoesNotExistException extends RuntimeException{
    public CustomerIdDoesNotExistException(String s){
        super(s);
    }
}
