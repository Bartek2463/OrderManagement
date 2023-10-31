package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.User;
import com.example.ordermanagement.model.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    private User users;


    @BeforeEach
    public void setUp() {
        users = User.builder()
        .userName("Janusz")
                .password("password")
                .email("kowalski@gmail.com")
                .firstName("Jan")
                .lastName("Kowalski")
                .userRole(UserRole.USER)
                .build();
    }
@DisplayName("junit test for same user operation")
    @Test
    public void givenUserObject_whenSave_ThenReturnSavedUser() {
        //when
        User saveUser = userRepository.save(users);
        //then

        Assertions.assertThat(saveUser).isNotNull();
    }

    @Test
}
