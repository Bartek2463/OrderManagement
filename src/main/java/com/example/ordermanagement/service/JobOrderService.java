package com.example.ordermanagement.service;


import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.order.JobOrder;

public interface JobOrderService {

    JobOrderDetailsDTO saveUser(JobOrder jobOrder);
}
