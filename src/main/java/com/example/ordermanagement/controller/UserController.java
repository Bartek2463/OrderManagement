package com.example.ordermanagement.controller;

import com.example.ordermanagement.exception.BadCredentialsException;
import com.example.ordermanagement.exception.BadRequestException;
import com.example.ordermanagement.exception.ElementAlreadyExistsException;
import com.example.ordermanagement.exception.UserRestExceptionHandler;
import com.example.ordermanagement.model.DTO.UserLoginDTO;
import com.example.ordermanagement.model.User;
import com.example.ordermanagement.service.AutheticationService;
import com.example.ordermanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRestExceptionHandler exceptionHandler;

    @Autowired
    private AutheticationService autheticationService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User users) {


        if (users.getUserName() == null || users.getPassword() == null || users.getLastName() == null || users.getFirstName() == null ||
                users.getEmail() == null) {
            return exceptionHandler.handleException(HttpStatus.BAD_REQUEST, new BadRequestException());
        }

        if (userService.searchUserName(users.getUserName()).isPresent() || userService.searchUserEmail(users.getEmail()).isPresent()) {
            return exceptionHandler.handleException(HttpStatus.CONFLICT, new ElementAlreadyExistsException("User"));
        }


        return new ResponseEntity<>(userService.saveUser(users), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        Optional<User> usersOpt = userService.searchUserName(userLoginDTO.getUserName());

        if (!usersOpt.isPresent()) {
            return exceptionHandler.handleException(HttpStatus.BAD_REQUEST, new BadCredentialsException("Bad credentials provided"));
        }
        return new ResponseEntity<>(autheticationService.signInAndReturnJWT(UserLoginDTO.mapToModel(userLoginDTO)),HttpStatus.OK);
        //todo
    }

}
