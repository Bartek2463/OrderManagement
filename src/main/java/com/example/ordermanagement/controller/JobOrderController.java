package com.example.ordermanagement.controller;

import com.example.ordermanagement.exception.ElementNotFoundException;
import com.example.ordermanagement.exception.EmptyListException;
import com.example.ordermanagement.exception.UserRestExceptionHandler;
import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.order.dto.JobOrderListDTO;
import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.model.user.dto.UserListDTO;
import com.example.ordermanagement.service.JobOrderService;
import com.example.ordermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    public ResponseEntity<?> saveJobOrder(@RequestBody JobOrderDetailsDTO jobOrderDetailsDTO, @PathVariable("ownerId") Long ownerId) {
        Optional<User> findUser = userService.searchById(ownerId);
        if (findUser.isEmpty()) {
            return exceptionHandler.handleException(
                    HttpStatus.NOT_FOUND, new ElementNotFoundException("User", "ID", ownerId.toString())
            );
        }
        return new ResponseEntity<>(jobOrderService.saveJobOrder(jobOrderDetailsDTO, ownerId), HttpStatus.OK);
    }


    @GetMapping("/owners/allOrders")
    public ResponseEntity<?> getAllOrders() {
        List<JobOrderListDTO> allJobOrders = jobOrderService.getAllJobOrders();
        if (allJobOrders.isEmpty()) {
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND, new EmptyListException("JobOrders"));
        }
        return new ResponseEntity<>(allJobOrders, HttpStatus.ACCEPTED);
    }


    @GetMapping("/owners/{ownerId}/allOrders")
    public ResponseEntity<?> getAllOrdersForSpecialClient(@PathVariable Long ownerId){
        Optional<User> findUser = userService.searchById(ownerId);
        if (findUser.isEmpty()){
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND,new ElementNotFoundException("User","ID",ownerId.toString()));
        }
        List<JobOrderListDTO> allJobOrders = jobOrderService.getAllJobOrders();
        if (allJobOrders.isEmpty()){
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND,new EmptyListException("JobOrders"));
        }
        return new ResponseEntity<>(allJobOrders,HttpStatus.ACCEPTED);
    }
}
