package com.example.ordermanagement.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("Invalid arguments provided");
    }
}
