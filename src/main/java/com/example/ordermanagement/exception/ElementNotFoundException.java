package com.example.ordermanagement.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String stringModel, String stringValue, String stringInput) {
        super(stringModel + " not found with given "+ stringValue+": "+stringInput);
    }
}
