package com.example.ordermanagement.service;

import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDtoUP;
import com.example.ordermanagement.model.order.dto.JobOrderListDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobOrderServiceImpl implements JobOrderService {
    @Override
    public JobOrderDetailsDTO saveJobOrder(JobOrder jobOrder) {
        return null;
    }

    @Override
    public Optional<JobOrder> searchJobOrderDate(Date date) {
        return Optional.empty();
    }

    @Override
    public Optional<JobOrder> searchJobOrderPrice(BigDecimal price) {
        return Optional.empty();
    }

    @Override
    public Optional<JobOrder> searchById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<JobOrderListDTO> getAllJobOrders() {
        return null;
    }

    @Override
    public JobOrderDetailsDtoUP updateByid(JobOrderDetailsDtoUP jobOrderDetailsDtoUp, Long id) {
        return null;
    }

    @Override
    public void delete(Long jobId) {

    }
}
