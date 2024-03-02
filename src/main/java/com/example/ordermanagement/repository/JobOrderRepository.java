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

    Optional<JobOrder> findByPrice(BigDecimal price);

    Optional<JobOrder> findByDateJobOrder(LocalDate date);

    @Query("select u from JobOrder u where u.price=:price and  u.startJobOrder=:startJobOrder and u.endJobOrder=:endJobOrder")
    Optional<JobOrder> findByJPQLPriceAndDateOrder(@Param("price") BigDecimal price, @Param("startJobOrder") LocalDate startJobOrder,@Param("endJobOrder")LocalDate endJobOrder);

}
