package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.order.JobOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface JobOrderRepository extends JpaRepository<JobOrder,Long> {

    Optional<JobOrder> findByPrice(BigDecimal price);
    Optional<JobOrder> findByDateJobOrder(LocalDate date);

}
