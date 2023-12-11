package com.example.ordermanagement.model.order.dto;

import com.example.ordermanagement.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JobOrderDetailsDTO {

    private BigDecimal price;
    private LocalDate dateJobOrder;
    @JsonIgnore
    private User user;
}
