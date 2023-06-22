package com.example.ordermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Order {

    @GetMapping("/{order}")
    public String getOrder(){
        return "Twice single page";
    }

}
