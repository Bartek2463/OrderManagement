package com.example.ordermanagement.deleteException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SuccesseDeleteHandler {

    @ExceptionHandler
    public ResponseEntity<SuccessDeleteResponse> handleDelete(HttpStatus httpStatus, Exception ex) {

        return new ResponseEntity<>(new SuccessDeleteResponse(httpStatus.value(),
                ex.getMessage(), System.currentTimeMillis()), HttpStatus.OK);
    }
}
