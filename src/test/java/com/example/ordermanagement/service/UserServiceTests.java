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


    private User user;

    @BeforeEach
    public void setup() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        user = new User();
        user.setEmail("email")
                .setId(1L)
                .setUserName("username")
                .setFirstName("Jan")
                .setLastName("Kowalski");

        user.setPassword(encodedPassword);
        userService = new UserServiceImpl(passwordEncoder, userRepository);

    }



    @DisplayName("Junit test for savedUser method")
    @Test
    public void givenUserObject_whenSavedUser_thenReturnUserObject() {
        //given - precondition or setup
      given(userRepository.saveAndFlush(user)).willReturn(user);
        //when - action or the behaviour that we are going test

        UserRegisterDTO savedUserRegisterDTO = userService.saveUser(user);

        //then - verify the output
       Assertions.assertThat(savedUserRegisterDTO).isNotNull();
       Assertions.assertThat(savedUserRegisterDTO).isExactlyInstanceOf(UserRegisterDTO.class);
       Assertions.assertThat(savedUserRegisterDTO.getId()).isEqualTo(1l);
       Assertions.assertThat(savedUserRegisterDTO.getRole()).isEqualTo(UserRole.USER);
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
                .build();
        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.of(user));
        given(userRepository.save(user)).willReturn(user);
        //when - action or the behaviour that we are going test

        UserRegisterDTO savedUserRegisterDTO = userService
                .saveUser(user);

        //then - verify the output
//        Assertions.assertThat(savedUserRegisterDTO).isEqualTo(UserRole.OWNER);
    }

    //junit test for
    @Test
    public void given_when_then() {
        //given - precondition or setup

        //when - action or the behaviour that we are going test

        //then - verify the output
    }

}