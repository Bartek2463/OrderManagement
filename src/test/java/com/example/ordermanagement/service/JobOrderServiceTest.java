package com.example.ordermanagement.service;

import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.order.dto.JobOrderDetailsDTO;
import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.repository.JobOrderRepository;
import com.example.ordermanagement.repository.UserRepository;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

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
    @DisplayName("Junit test for saveJobOrder method which find User id")
    @Test
    public void givenJobOrderObject_whenSaveJobOrder_thenReturnJobOrderObject() {
        //given - precondition or setup

        BDDMockito.given(jobOrderRepository.save(jobOrder)).willReturn(jobOrder);
        BDDMockito.given(userRepository.findById(1l)).willReturn(Optional.of(user));
//
//             //when - action or the behaviour that we are going test
        JobOrderDetailsDTO jobOrderDetailsDTO = jobOrderService.saveJobOrder(jobOrder, user.getId());
        //then - verify the output
        Assertions.assertThat(jobOrderDetailsDTO).isNotNull();
        Assertions.assertThat(jobOrderDetailsDTO).isExactlyInstanceOf(JobOrderDetailsDTO.class);
        Assertions.assertThat(jobOrderDetailsDTO.getId()).isEqualTo(1l);
        Assertions.assertThat(jobOrderDetailsDTO.getPrice()).isEqualTo(BigDecimal.valueOf(150));
    }


    //junit test for
    @DisplayName(" Junit test for search Order By date method which find Date is Exist ")
    @Test
    public void givenJobOrder_whenSearchOrderByDate_thenReturnJobOrderObject() {
        //given - precondition or setup
        LocalDate savedDateJobOrder = LocalDate.of(2022, 02, 12);
        BDDMockito.given(jobOrderRepository.findByDateJobOrder(savedDateJobOrder)).willReturn(Optional.of(jobOrder));
        //when - action or the behaviour that we are going test
        Optional<JobOrder> saveJobOrder = jobOrderService.searchJobOrderDate(savedDateJobOrder);

        //then - verify the output

        Assertions.assertThat(saveJobOrder).isNotNull();
        Assertions.assertThat(saveJobOrder.get()).isEqualTo(jobOrder);
    }

    //junit test for
    @DisplayName("Junit test for search Order By date method which find Date is Not exist")
    @Test
    public void givenJobOrder_whenSearchOrderByDate_thenReturnOptionalEmpty() {
        //given - precondition or setup
        LocalDate savedDate = LocalDate.of(2022, 02, 12);

        BDDMockito.given(jobOrderRepository.findByDateJobOrder(savedDate)).willReturn(Optional.empty());
        //when - action or the behaviour that we are going test
        Optional<JobOrder> savedJobOrder = jobOrderService.searchJobOrderDate(savedDate);
        //then - verify the output
        Assertions.assertThat(savedJobOrder).isEmpty();
    }

    //junit test for
    @DisplayName("Junit test for search Order By Price method which find Price is exist")
    @Test
    public void givenJobOrder_whenSearchOrderByPrice_thenReturnJobOrderObject() {
        //given - precondition or setup
        BigDecimal priceOrder = BigDecimal.valueOf(150);
        BDDMockito.given(jobOrderRepository.findByPrice(priceOrder)).willReturn(Optional.of(jobOrder));
        //when - action or the behaviour that we are going test
        Optional<JobOrder> savedJobOfPrice = jobOrderService.searchJobOrderPrice(priceOrder);

        //then - verify the output

        Assertions.assertThat(savedJobOfPrice).isNotNull();
        Assertions.assertThat(savedJobOfPrice.get()).isEqualTo(jobOrder);
        Assertions.assertThat(savedJobOfPrice.get().getPrice()).isEqualTo(priceOrder);
    }

    //junit test for
    @DisplayName("Junit test for search Order By Price method which find Price is not Exist")
    @Test
    public void givenJobOrder_whenSearchOrderByPrice_thenReturnJobOrderOptionalEmpty() {
        //given - precondition or setup
        BigDecimal priceOrder = BigDecimal.valueOf(150);
        BDDMockito.given(jobOrderRepository.findByPrice(priceOrder)).willReturn(Optional.empty());
        //when - action or the behaviour that we are going test
        Optional<JobOrder> savedJobOrder = jobOrderService.searchJobOrderPrice(priceOrder);
        //then - verify the output
        Assertions.assertThat(savedJobOrder).isEmpty();
    }



}