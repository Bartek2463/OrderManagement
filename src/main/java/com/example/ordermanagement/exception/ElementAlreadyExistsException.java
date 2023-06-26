package com.example.ordermanagement.exception;

public class ElementAlreadyExistsException extends Exception {
    public ElementAlreadyExistsException(String userModel) {
        super(userModel+" already exists in the database");
    }
}
