package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserLoginDTO;
import com.example.ordermanagement.model.Users;

public interface AutheticationService {

    UserLoginDTO signInAndReturnJWT(Users signInRequest);

    boolean isAdmin();
    boolean idLogged();

}
