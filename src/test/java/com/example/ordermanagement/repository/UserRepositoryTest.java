package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.UserRole;
import com.example.ordermanagement.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    private Users users;


    @BeforeEach
    public void setUp() {
        users = new Users();
        users.setId(1l)
                .setUserName("Janusz")
                .setPassword("passwordst")
                .setEmail("kowalski@gmail.com")
                .setFirstName("Jan")
                .setLastName("Kowalski")
                .setUserRole(UserRole.USER);
    }

    @Test
    public void givenUsersObject_whenSave_ThenReturnSavedUser() {

        //given

        //when
        Users save = userRepository.save(users);
        //then
    }
}
