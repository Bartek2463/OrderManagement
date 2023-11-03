package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserListDTO;
import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.User;
import com.example.ordermanagement.model.UserRole;
import com.example.ordermanagement.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {


    @Mock
     private UserRepository userRepository;

    @InjectMocks
     private UserServiceImpl userService;

    private User user;

    @BeforeEach
    private void setUp(){
        user = User.builder()
                .userName("John")
                .password("password")
                .email("kowalski@gmail.com")
                .firstName("Jan")
                .lastName("Kowalski")
                .userRole(UserRole.USER)
                .build();
    }

    //Junit test for saveEmployee method
    //junit test for
         @Test
         public void givenUserObject_whenSavedUser_thenReturnUserObject(){
            //given - precondition or setup
              given(userRepository.findByEmail(user.getEmail()))
                      .willReturn(Optional.empty());
              given(userRepository.save(user)).willReturn(user);
             //when - action or the behaviour that we are going test

             UserRegisterDTO savedUserRegisterDTO = userService.saveUser(user);




             //then - verify the output
             Assertions.assertThat(savedUserRegisterDTO).isNotNull();
         }
}