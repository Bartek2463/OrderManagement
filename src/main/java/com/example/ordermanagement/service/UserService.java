package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.Users;

import java.util.Optional;

public interface UserService {
    UserRegisterDTO saveUser(Users users);

    Optional<Users> searchUserName(String name);
    Optional<Users> searchUserEmail(String email);
}