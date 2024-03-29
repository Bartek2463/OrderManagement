package com.example.ordermanagement.model.user;


import com.example.ordermanagement.model.order.JobOrder;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity


@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_name"}),
        @UniqueConstraint(columnNames = {"email"})})
@Accessors(chain = true, fluent = false)
@Builder
@ToString

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @NonNull
    private String userName;

    @Column(name = "password", nullable = false)
    @NonNull
    @Size(min = 8, max = 14, message = "Password must have contain at least 8 characters or less 14 characters")
    private String password;
    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    @Transient
    private String token;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE},
            mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<JobOrder> ordersList;


}
