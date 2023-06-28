package com.example.ordermanagement.deleteException;

public class SuccesseDelete extends RuntimeException {
    public SuccesseDelete() {
        super("Delete successful");
    }
}
