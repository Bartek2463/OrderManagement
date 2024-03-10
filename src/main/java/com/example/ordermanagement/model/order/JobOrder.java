package com.example.ordermanagement.model.order;

import com.example.ordermanagement.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "jobOrder")
@Accessors(chain = true, fluent = false)
@Builder
@ToString
public class JobOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "price_job_order", nullable = false)
//    @NonNull
    private Integer price;

    @Column(name = "start_job_order")
    private LocalDate startJobOrder;

    @Column(name = "end_job_order", nullable = false)
    private LocalDate endJobOrder;


    @Column
    @NonNull
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

}