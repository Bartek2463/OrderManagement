package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OwnerUserController {

    @Autowired
    UserService userService;



}
