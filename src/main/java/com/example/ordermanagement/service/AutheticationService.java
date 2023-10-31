package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserLoginDTO;
import com.example.ordermanagement.model.User;

public interface AutheticationService {

    UserLoginDTO signInAndReturnJWT(User signInRequest);

    boolean isOwner();
    boolean idLogged();

}
