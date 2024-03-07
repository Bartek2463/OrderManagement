package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.order.JobOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface JobOrderRepository extends JpaRepository<JobOrder, Long> {

    Optional<JobOrder> findByPrice(Integer price);

//    Optional<JobOrder> findByDateJobOrder(LocalDate startDate);

    @Query("select u from JobOrder u where u.price=:price and  u.startJobOrder=:startJobOrder")
    Optional<JobOrder> findByJPQLPriceAndDateOrder(@Param("price") Integer price, @Param("startJobOrder") LocalDate startJobOrder);

}
