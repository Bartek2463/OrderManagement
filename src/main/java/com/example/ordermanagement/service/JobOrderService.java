package com.example.ordermanagement.service;


import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDtoUP;
import com.example.ordermanagement.model.order.dto.JobOrderListDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JobOrderService {

    JobOrderDetailsDTO saveJobOrder(JobOrderDetailsDTO jobOrderDetailsDTO,Long id);

//    Optional<JobOrder> searchJobOrderDate(LocalDate startDate);
    Optional<JobOrder> searchJobOrderPrice(Integer price);

    Optional<JobOrder> searchById(Long id);
    List<JobOrderListDTO> getAllJobOrders();

    JobOrderDetailsDtoUP updateByid(JobOrderDetailsDtoUP jobOrderDetailsDtoUp,Long id);

    void delete(Long jobId);
}
