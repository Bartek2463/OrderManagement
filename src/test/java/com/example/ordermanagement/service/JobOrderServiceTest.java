package com.example.ordermanagement.service;

import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.repository.JobOrderRepository;
import com.example.ordermanagement.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class JobOrderServiceTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JobOrderRepository jobOrderRepository;


    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    private JobOrderServiceImpl jobOrderService;

    private User user;
    private JobOrder jobOrder;

    @BeforeEach
    public void setup() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        user = new User();
        user.setEmail("email")
                .setId(1L)
                .setUserName("username")
                .setFirstName("Jan")
                .setNIP("")
                .setLastName("Kowalski");

        user.setPassword(encodedPassword);
        userService = new UserServiceImpl(passwordEncoder, userRepository);


        jobOrder = new JobOrder();
        jobOrder.setId(1l)
                .setPrice(BigDecimal.valueOf(150))
                .setDateJobOrder(LocalDate.of(2022, 02, 12))
                .setDescription("Order about car repair ")
                .setUser(user);
    }

    //junit test for
    @Test
    public void givenJobOrderObject_whenSaveJobOrder_thenReturnJobOrderObject() {
        //given - precondition or setup

        BDDMockito.given(jobOrderRepository.save(jobOrder)).willReturn(jobOrder);
//

//             //when - action or the behaviour that we are going test

        JobOrderDetailsDTO jobOrderDetailsDTO = jobOrderService.saveJobOrder(jobOrder, user.getId());


        //then - verify the output
        Assertions.assertThat(jobOrderDetailsDTO).isNotNull();
    }
}