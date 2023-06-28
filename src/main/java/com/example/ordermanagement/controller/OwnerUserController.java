package com.example.ordermanagement.controller;

import com.example.ordermanagement.exception.ElementNotFoundException;
import com.example.ordermanagement.exception.EmptyListException;
import com.example.ordermanagement.exception.UserRestExceptionHandler;
import com.example.ordermanagement.model.DTO.UserDetailsDTO;
import com.example.ordermanagement.repository.UserRepository;
import com.example.ordermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerUserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRestExceptionHandler exceptionHandler;


    @PatchMapping("/owners/{ownerId}")
    public ResponseEntity<?> updateOwnUserByid(@PathVariable("ownerId")Long id, @RequestBody UserDetailsDTO userDetailsDTO) {

        UserDetailsDTO updateUserDetailsDTO = userService.updateById(userDetailsDTO, id);

        if (updateUserDetailsDTO==null){
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND,new ElementNotFoundException("User","ID",id.toString()));
        }

        return new ResponseEntity<>(userDetailsDTO,HttpStatus.ACCEPTED);
    }


}
