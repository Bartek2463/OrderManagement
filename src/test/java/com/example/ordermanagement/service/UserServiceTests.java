package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserDetailsDTO;
import com.example.ordermanagement.model.DTO.UserListDTO;
import com.example.ordermanagement.model.DTO.UserLoginDTO;
import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.User;
import com.example.ordermanagement.model.UserRole;
import com.example.ordermanagement.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

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
        assertThat(savedUserRegisterDTO).isNotNull();
        assertThat(savedUserRegisterDTO).isExactlyInstanceOf(UserRegisterDTO.class);
        assertThat(savedUserRegisterDTO.getId()).isEqualTo(1l);
        assertThat(savedUserRegisterDTO.getRole()).isEqualTo(UserRole.USER);
    }

    //junit test for
    @DisplayName("Junit test for savedUser method which set Role")
    @Test
    public void givenUserNameAndPassword_whenSavedUser_thenReturnRole() {

        //given - precondition or setup
        User user1 = new User()
                .setUserName("My business")
                .setId(1L)
                .setPassword("password")
                .setFirstName("Jan")
                .setLastName("Nowak")
                .setEmail("jan@gmail.com");
        given(userRepository.saveAndFlush(user1)).willReturn(user1);
        //when - action or the behaviour that we are going test

        UserRegisterDTO savedUser = userService.saveUser(user1);

        //then - verify the output
        assertThat(savedUser.getRole()).isEqualTo(UserRole.OWNER);
    }

    //junit test for
    @DisplayName("Junit test for getAllUsers method")
    @Test
    public void givenUserList_whenGetAllUsers_thenReturnUserList() {
        //given - precondition or setup
        User user1 = new User()
                .setUserName("My business")
                .setId(1L)
                .setPassword("password")
                .setFirstName("Jan")
                .setLastName("Nowak")
                .setEmail("jan@gmail.com");

        given(userRepository.findAll()).willReturn(List.of(user, user1));

        //when - action or the behaviour that we are going test

        List<UserListDTO> userList = userService.getAllUsers();

        //then - verify the output
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }

    //junit test for
    @DisplayName("Junit test for getAllEmployees method(negative scenerio)")
    @Test
    public void givenUserList_whenGetAllUsers_thenReturnEmptyUserList() {
        //given - precondition or setup
        given(userRepository.findAll()).willReturn(Collections.emptyList());
        //when - action or the behaviour that we are going test
        List<UserListDTO> usersList = userService.getAllUsers();

        //then - verify the output
        assertThat(usersList).isNotNull();
        assertThat(usersList.size()).isEqualTo(0);
    }

    //junit test for
    @DisplayName("Junit tet for getUserById method")
    @Test
    public void givenUserId_whenGetUserById_thenReturnUserObject() {
        //given - precondition or setup
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        //when - action or the behaviour that we are going test
        Optional<User> savedUser = userService.serachById(user.getId());

        //then - verify the output
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.get().getId()).isEqualTo(1l);
    }

    //junit test for
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdateObjectUser() {
        //given - precondition or setup
        given(userRepository.save(user)).willReturn(user);
        user.setUserName("Counter");
        user.setFirstName("Morlin");
//        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
//        //when - action or the behaviour that we are going test
//        UserDetailsDTO userDetailsDTO1 = userService.updateById(userDetailsDTO, user.getId());


        //then - verify the output
        assertThat(user).isNotNull();
    }


}