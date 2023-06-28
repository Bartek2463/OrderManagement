package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserDetailsDTO;
import com.example.ordermanagement.model.DTO.UserListDTO;
import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserRegisterDTO saveUser(Users users);

    Optional<Users> searchUserName(String name);
    Optional<Users> searchUserEmail(String email);

    Optional<Users> serachById(Long ownerId);

    List<UserListDTO> getAllUsers();

    UserDetailsDTO updateById(UserDetailsDTO detailsDto, Long id);
    void delete(Long appId);
}