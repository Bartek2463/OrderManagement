package com.example.ordermanagement.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameUser"}),
        @UniqueConstraint(columnNames = {"email"})})
@Accessors(chain = true,fluent = false)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private UserRole role;



}
