package com.example.ordermanagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Table(name = "User",uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"username"}),
//        @UniqueConstraint(columnNames ={"emai"})
//})
@Accessors(chain = true,fluent = false)

public class Users {

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
    private UserRole userRole;

    @Transient
    private String token;


}
