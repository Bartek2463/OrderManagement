package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.User;
import com.example.ordermanagement.model.UserRole;
import com.example.ordermanagement.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {


    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;


    private User user;

    @BeforeEach
    public void setUp() {

        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode("password");
        user = User.builder()
                .userName("John")
                .email("kowalski@gmail.com")
                .firstName("Jan")
                .lastName("Kowalski")
                .userRole(UserRole.USER)
                .build();

        user.setPassword(encodePassword);
        userService = new UserServiceImpl(userRepository,passwordEncoder);
    }



    @DisplayName("Junit test for savedUser method")
    @Test
    public void givenUserObject_whenSavedUser_thenReturnUserObject() {
        //given - precondition or setup
        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.empty());
        given(userRepository.save(user)).willReturn(user);
        given(userService.)
        //when - action or the behaviour that we are going test

        UserRegisterDTO savedUserRegisterDTO = userService.saveUser(user);

        //then - verify the output
        Assertions.assertThat(savedUserRegisterDTO).isNotNull();
    }

    //junit test for
    @DisplayName("Junit test for savedUser method which set Role")
    @Test
    public void givenUserNameAndPassword_whenSavedUser_thenReturnRole() {

        //given - precondition or setup
        user = User.builder()
                .userName("My business")
                .password("password")
                .email("kowalski@gmail.com")
                .firstName("Jan")
                .lastName("Kowalski")
                .userRole(UserRole.USER)
                .build();
        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.of(user));
        given(userRepository.save(user)).willReturn(user);
        //when - action or the behaviour that we are going test

        UserRegisterDTO savedUserRegisterDTO = userService
                .saveUser(user);

        //then - verify the output
        Assertions.assertThat(savedUserRegisterDTO).isEqualTo(UserRole.OWNER);
    }

    //junit test for
    @Test
    public void given_when_then() {
        //given - precondition or setup

        //when - action or the behaviour that we are going test

        //then - verify the output
    }

}