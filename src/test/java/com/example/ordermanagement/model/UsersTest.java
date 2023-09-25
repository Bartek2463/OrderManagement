package com.example.ordermanagement.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    private Users users;
    @BeforeEach
    void setUp() {
        users = new Users();
    }

    @Test
    void shouldReturnEmptyOrBlank  (){
        //given


        //when
        users.setUserName("");



        //then


    }

    @AfterEach
    void tearDown() {
    }
}