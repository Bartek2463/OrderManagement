package com.example.ordermanagement.service;

import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.model.user.dto.UserRegisterDto;

public interface UserSevice {
    UserRegisterDto saveUser(User user);
}
