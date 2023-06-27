package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.UserRole;
import com.example.ordermanagement.model.Users;
import com.example.ordermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

 @Autowired
    private UserRepository userRepository;
 @Autowired
 private PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterDTO saveUser(Users users) {
             users.setUserRole(UserRole.USER);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = userRepository.saveAndFlush(users);
        return UserRegisterDTO.mapToDto(savedUser);
    }

    @Override
    public Optional<Users> searchUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public Optional<Users> serachUserEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

