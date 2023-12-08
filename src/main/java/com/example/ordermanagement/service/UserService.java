package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserDetailsDTO;
import com.example.ordermanagement.model.DTO.UserListDTO;
import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.user.User;

import java.util.List;
import java.util.Optional;
public interface UserService {
    UserRegisterDTO saveUser(User users);

    Optional<User> searchUserName(String name);
    Optional<User> searchUserEmail(String email);

    Optional<User> searchById(Long ownerId);

    List<UserListDTO> getAllUsers();

    UserDetailsDTO updateById(UserDetailsDTO detailsDto, Long id);
    void delete(Long appId);
}