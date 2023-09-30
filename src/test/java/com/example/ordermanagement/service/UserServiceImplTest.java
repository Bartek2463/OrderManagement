package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserListDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void searchUserName() {
    }

    @Test
    void searchUserEmail() {
    }

    @Test
    void serachById() {
    }

    @Test
    void shouldReturnGetAllUsers() {
        UserService userService = mock(UserService.class);
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        given(userService.getAllUsers()).willReturn(Arrays.asList());


        List<UserListDTO> allUsers = userServiceImpl.getAllUsers();
        allUsers.add(0,1)

        assertThat(allUsers,is(null));

    }

    @Test
    void updateById() {
    }

    @Test
    void delete() {
    }
}