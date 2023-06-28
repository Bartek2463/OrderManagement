package com.example.ordermanagement.controller;

import com.example.ordermanagement.deleteException.SuccesseDelete;
import com.example.ordermanagement.exception.ElementNotFoundException;
import com.example.ordermanagement.exception.EmptyListException;
import com.example.ordermanagement.exception.UserRestExceptionHandler;
import com.example.ordermanagement.model.DTO.UserDetailsDTO;
import com.example.ordermanagement.model.DTO.UserListDTO;
import com.example.ordermanagement.model.Users;
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



    @GetMapping("/owners")
    public ResponseEntity<?> getOwnersList() {
        List<UserListDTO> allUsers = userService.getAllUsers();
        if (allUsers.isEmpty()) {
            return exceptionHandler.handleException(HttpStatus.NO_CONTENT, new EmptyListException("Owner"));

        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PatchMapping("/owners/{ownerId}")
    public ResponseEntity<?> updateOwnUserByid(@PathVariable("ownerId") Long id, @RequestBody UserDetailsDTO userDetailsDTO) {

        UserDetailsDTO updateUserDetailsDTO = userService.updateById(userDetailsDTO, id);

        if (updateUserDetailsDTO == null) {
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND, new ElementNotFoundException("User", "ID", id.toString()));
        }

        return new ResponseEntity<>(userDetailsDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<?> getOwnerByid(@PathVariable("ownerId")Long id){
        Optional<Users> usersOpt = userService.serachById(id);
        if (usersOpt.isEmpty()){
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND,new ElementNotFoundException("User","ID",id.toString()));
        }
        Users users = usersOpt.get();
        UserDetailsDTO userDetailsDTO = UserDetailsDTO.mapToDto(users);
        return  new ResponseEntity<>(userDetailsDTO,HttpStatus.OK);
    }

    @DeleteMapping("/owners/{ownerId}")
    public ResponseEntity<?> deleteByid(@PathVariable("ownerID")Long id){
        Optional<Users> usersOpt = userService.serachById(id);
        if (usersOpt.isEmpty()){
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND,new ElementNotFoundException("User","ID",id.toString()));
        }
        return new ResponseEntity<>(HttpStatus.OK,new SuccesseDelete());
    }


}
