package com.example.ordermanagement.exception;

import com.example.ordermanagement.error.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(HttpStatus httpStatus, Exception ex) {
        return new ResponseEntity<>(new UserErrorResponse(httpStatus.value(),
                ex.getMessage(),System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}

