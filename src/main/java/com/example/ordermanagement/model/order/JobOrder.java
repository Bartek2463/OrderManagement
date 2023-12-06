package com.example.ordermanagement.model.order;

import com.example.ordermanagement.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobOrder")
@Accessors(chain = true,fluent = true)
public class JobOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "price_job_order",nullable = false)
    @NonNull
    private BigDecimal price;
    @Column(name = "date_job_order",nullable = false)
    @NonNull
    private Date dateJobOrder;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;

}
