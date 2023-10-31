package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.User;
import com.example.ordermanagement.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    private User user;


    @BeforeEach
    public void setUp() {
        user = User.builder()
                .userName("John")
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
        User saveUser = userRepository.save(user);
        //then

        assertThat(saveUser).isNotNull();
        assertThat(saveUser.getId()).isGreaterThan(0);

    }

    @DisplayName("Junit test for get all users operation ")
    @Test
    public void givenUserList_whenFindAll_thenUserList() {
        //given
        User userSave1 = User.builder()
                .userName("Tomson")
                .password("password")
                .email("nowak@gmail.com")
                .firstName("Tomasz")
                .lastName("Nowak")
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);
        userRepository.save(userSave1);

        //when
        List<User> allUser = userRepository.findAll();
        //then
        assertThat(allUser).isNotNull();
        assertThat(allUser.size()).isEqualTo(2);
        assertThat(allUser.get(0).getUserName()).isNotSameAs(allUser.get(1).getUserName());
    }

   @DisplayName("Junit test for get employee by id operation")
     @Test
     public void givenUserObject_whenFindById_thenReturnUserObject(){
        //given - precondition or setup

         userRepository.save(user);
         //when - action or the behaviour that we are going test

         User userDB = userRepository.findById(user.getId()).get();
         //then - verify the output
         assertThat(userDB).isNotNull();
         assertThat(userDB.getUserRole()).isEqualTo(UserRole.USER);
         assertThat(userDB.getEmail()).isEqualTo("kowalski@gmail.com");
     }

     @Test

}


