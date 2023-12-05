package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.order.JobOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<JobOrder,Long> {
}
