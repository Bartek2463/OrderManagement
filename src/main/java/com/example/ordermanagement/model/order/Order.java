package com.example.ordermanagement.model.order;

import com.example.ordermanagement.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order")
@Accessors(chain = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOrder;
//    private BigDecimal price;
//    private Date dateOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @ToString.Exclude
    private User user;


}
