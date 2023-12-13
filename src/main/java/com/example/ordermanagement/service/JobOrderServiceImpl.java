package com.example.ordermanagement.service;

import com.example.ordermanagement.exception.ElementNotFoundException;
import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDtoUP;
import com.example.ordermanagement.model.order.dto.JobOrderListDTO;
import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.repository.JobOrderRepository;
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
    private UserService userService;
    @Override
    public JobOrderDetailsDTO saveJobOrder(JobOrderDetailsDTO jobOrderDetailsDTO, Long id) {

        Optional<User> optionalUser = userService.searchById(id);
        User user = optionalUser.orElseThrow(() -> new ElementNotFoundException("User", "id", id.toString()));
        jobOrderDetailsDTO.setUser(user);
        JobOrder jobOrder = jobOrderRepository.saveAndFlush(JobOrderDetailsDTO.mapToModel(jobOrderDetailsDTO));
        return JobOrderDetailsDTO.mapToDto(jobOrder);
    }

    @Override
    public Optional<JobOrderDetailsDTO> searchJobOrderDate(LocalDate localDate) {
        JobOrder jobOrderDatailsDTO = jobOrderRepository.findByDateJobOrder(localDate).orElseThrow(() -> new ElementNotFoundException("JobOrder", "localDate", localDate.toString()));
        return

    }

    @Override
    public Optional<JobOrderDetailsDTO> searchJobOrderPrice(BigDecimal price) {
        return ;
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
