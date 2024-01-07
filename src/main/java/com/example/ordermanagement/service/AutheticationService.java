package com.example.ordermanagement.service;

import com.example.ordermanagement.model.user.dto.UserLoginDTO;
import com.example.ordermanagement.model.user.User;
import org.springframework.stereotype.Service;

@Service
public interface AutheticationService {

    UserLoginDTO signInAndReturnJWT(User signInRequest);

    boolean isOwner();
    boolean idLogged();

}
