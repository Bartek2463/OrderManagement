package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserLoginDTO;
import com.example.ordermanagement.model.Users;
import com.example.ordermanagement.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AutheticationServiceImpl implements AutheticationService {

     @Autowired
     private AuthenticationManager authenticationManager;
     @Autowired
     private UserService userService;

     @Autowired
     private JwtProvider jwtProvider;



    @Override
    public UserLoginDTO signInAndReturnJWT(Users signInRequest) {
        return null;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public boolean idLogged() {
        return false;
    }

    @Override
    public boolean isUser() {
        return false;
    }

    @Override
    public boolean isOwner() {
        return false;
    }
}
