package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.Users;
import com.example.ordermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

 @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegisterDTO saveUser(Users users) {
        Users save = userRepository.save(users);
        return UserRegisterDTO.mapToDto(save);
    }
}

