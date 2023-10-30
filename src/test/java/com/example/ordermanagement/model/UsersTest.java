package com.example.ordermanagement.model;

import com.example.ordermanagement.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UsersTest {

    @Autowired
    private UserRepository userRepository;

    private Users users;


    @BeforeEach
    public void setUp(){
        users = Users.builder()
                .userName("Janek")
                .password("password")
                .email("kowalski@gmail.com")
                .firstName("Jan")
                .lastName("Kowalski")
                .userRole(UserRole.USER)
                .build();

    }

    @Test
    public void givenUserObject_whenSave_thenReturnSavedUser(){
        //given

        //when
        Users saveUser = userRepository.save(users);
        //then
//        Assertions.assertThat(saveUser).isNotNull();
    }

}