package com.example.ordermanagement.exception;

import javax.security.sasl.AuthenticationException;

public class BadCredentialsException extends AuthenticationException {
    public BadCredentialsException(String message) {
        super(message);
    }

    public BadCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}