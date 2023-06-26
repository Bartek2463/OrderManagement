package com.example.ordermanagement.controller;

import com.example.ordermanagement.exception.BadRequestException;
import com.example.ordermanagement.exception.ElementAlreadyExistsException;
import com.example.ordermanagement.exception.UserRestExceptionHandler;
import com.example.ordermanagement.model.Users;
import com.example.ordermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRestExceptionHandler exceptionHandler;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users users) {


        if (users.getUserName() == null || users.getPassword() == null || users.getLastName() == null || users.getFirstName() == null ||
                users.getEmail() == null) {
            return exceptionHandler.handleException(HttpStatus.BAD_REQUEST, new BadRequestException());
        }

        if (userService.searchUserName(users.getUserName()).isPresent() || userService.serachUserEmail(users.getEmail()).isPresent()) {
            return exceptionHandler.handleException(HttpStatus.CONFLICT, new ElementAlreadyExistsException("User"));
        }


        return new ResponseEntity<>(userService.saveUser(users), HttpStatus.CREATED);
    }


}
