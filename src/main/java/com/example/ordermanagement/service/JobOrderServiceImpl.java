package com.example.ordermanagement.service;

import com.example.ordermanagement.exception.ElementNotFoundException;
import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDtoUP;
import com.example.ordermanagement.model.order.dto.JobOrderListDTO;
import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.repository.JobOrderRepository;
import com.example.ordermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JobOrderServiceImpl implements JobOrderService {

    @Autowired
    private JobOrderRepository jobOrderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public JobOrderDetailsDTO saveJobOrder(JobOrderDetailsDTO dto, Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("JobOrder", "ID", id.toString()));
        dto.setUser(user);
        JobOrder save = jobOrderRepository.save(JobOrderDetailsDTO.mapToModel(dto));
        return JobOrderDetailsDTO.mapToDto(save);
    }

    @Override
    public Optional<JobOrder> searchJobOrderDate(LocalDate localDate) {
        return jobOrderRepository.findByDateJobOrder(localDate);
    }

    @Override
    public Optional<JobOrder> searchJobOrderPrice(BigDecimal price) {
        return jobOrderRepository.findByPrice(price);
    }

    @Override
    public Optional<JobOrder> searchById(Long id) {
        return jobOrderRepository.findById(id);
    }

    @Override
    public List<JobOrderListDTO> getAllJobOrders() {
        List<JobOrder> allJobs = jobOrderRepository.findAll();
        return JobOrderListDTO.mapToDto(allJobs);
    }

    @Override
    public JobOrderDetailsDtoUP updateByid(JobOrderDetailsDtoUP jobOrderDetailsDtoUp, Long id) {
        Optional<JobOrder> jobOrder = searchById(id);
        if (jobOrder.isEmpty()) {
            return null;
        }
        jobOrderDetailsDtoUp.setDateJobOrder(jobOrderDetailsDtoUp.getDateJobOrder());
        jobOrderDetailsDtoUp.setPrice(jobOrderDetailsDtoUp.getPrice());
        jobOrderDetailsDtoUp.setDescription(jobOrderDetailsDtoUp.getDescription());
        JobOrder saveEditJobOrder = jobOrderRepository.save(jobOrder.get());
        return JobOrderDetailsDtoUP.mapToDto(saveEditJobOrder);
    }

    @Override
    public void delete(Long jobId) {
        jobOrderRepository.deleteById(jobId);
    }
}
