package com.example.ordermanagement.service;

import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.model.user.dto.UserRegisterDto;
import com.example.ordermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSevice {

@Autowired
    private UserRepository userRepository;

    @Override
    public UserRegisterDto saveUser(User user) {
        User saveUser = userRepository.save(user);
        return UserRegisterDto.mapToDto(saveUser);
    }
}
