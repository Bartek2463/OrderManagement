package com.example.ordermanagement.controller;

import com.example.ordermanagement.deleteException.SuccesseDelete;
import com.example.ordermanagement.deleteException.SuccesseDeleteHandler;
import com.example.ordermanagement.exception.*;
import com.example.ordermanagement.model.user.dto.UserDetailsDTO;
import com.example.ordermanagement.model.user.dto.UserListDTO;
import com.example.ordermanagement.model.user.dto.UserLoginDTO;
import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.service.AutheticationService;
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
    UserRestExceptionHandler exceptionHandler;

    @Autowired
    SuccesseDeleteHandler deleteHandler;
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
            return exceptionHandler.handleException(
                    HttpStatus.NOT_FOUND, new ElementNotFoundException("User", "ID", id.toString()));
        }

        return new ResponseEntity<>(userDetailsDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<?> getOwnerByid(@PathVariable("ownerId") Long id) {
        Optional<User> usersOpt = userService.searchById(id);
        if (usersOpt.isEmpty()) {
            return exceptionHandler.handleException(
                    HttpStatus.NOT_FOUND, new ElementNotFoundException("User", "ID", id.toString()));
        }
        User users = usersOpt.get();
        UserDetailsDTO userDetailsDTO = UserDetailsDTO.mapToDto(users);
        return new ResponseEntity<>(userDetailsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/owners/{ownerId}")
    public ResponseEntity<?> deleteByid(@PathVariable("ownerId") Long id) {
        Optional<User> usersOpt = userService.searchById(id);
        if (usersOpt.isEmpty()) {
            return exceptionHandler.handleException(
                    HttpStatus.NOT_FOUND, new ElementNotFoundException("User", "ID", id.toString()));
        }
        userService.delete(id);
        return deleteHandler.handleDelete(HttpStatus.OK, new SuccesseDelete());
    }
}
