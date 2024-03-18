package com.example.ordermanagement.controller;

import com.example.ordermanagement.exception.ElementNotFoundException;
import com.example.ordermanagement.exception.UserRestExceptionHandler;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.service.JobOrderService;
import com.example.ordermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JobOrderController {

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestExceptionHandler exceptionHandler;

   @PostMapping("/owners/{ownerId}/addJobOrder")

    public ResponseEntity<?> saveJobOrder(@RequestBody JobOrderDetailsDTO jobOrderDetailsDTO, @PathVariable("ownerId")Long ownerId ){
       Optional<User> findUser = userService.searchById(ownerId);
         if (findUser.isEmpty()){
           return exceptionHandler.handleException(
                   HttpStatus.NOT_FOUND,new ElementNotFoundException("User","ID",ownerId.toString())
           );
         }
         return new ResponseEntity<>(jobOrderService.saveJobOrder(jobOrderDetailsDTO,ownerId),HttpStatus.OK);


   }
}
