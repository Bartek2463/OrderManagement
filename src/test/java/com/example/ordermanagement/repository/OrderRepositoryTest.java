package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.model.user.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private JobOrderRepository jobOrderRepository;
    private JobOrder jobOrder;
    private User user;


    @BeforeEach
    public void setUpOrder() {
        user = User.builder()
                .userName("John")
                .password("password")
                .email("kowalski@gmail.com")
                .firstName("Jan")
                .lastName("Kowalski")
                .ordersList(List.of())
                .userRole(UserRole.USER)
                .build();


        jobOrder = JobOrder.builder()
                .startJobOrder(LocalDate.of(2020, 12, 02))
                .endJobOrder(LocalDate.of(2020, 12, 12))
                .price(12)
                .description("Change tire")
                .user(user)
                .build();
    }

    //junit test for
    @DisplayName("junit test for same user operation")
    @Test
    public void givenJobOrderObject_whenSave_thenReturnSavedJobOrder() {
        //given - precondition or setup
        //when - action or the behaviour that we are going test
        JobOrder saveOrder = jobOrderRepository.save(jobOrder);

        //then - verify the output
        Assertions.assertThat(saveOrder).isNotNull();
        Assertions.assertThat(saveOrder.getId()).isGreaterThan(0);
    }

    //junit test for
    @DisplayName("Junit test for get all users operation ")
    @Test
    public void givenUserAndJobOrder_whenFindAllJobOrder_then() {
        //given - precondition or setup

        JobOrder saveJobOrder1 = jobOrder = JobOrder.builder()
                .startJobOrder(LocalDate.of(2020, 12, 02))
                .endJobOrder(LocalDate.of(2020, 12, 12))
                .price(30)
                .description("chanhed tire")
                .user(user)
                .build();
        JobOrder saveJobOrder2 = jobOrder = JobOrder.builder()
                .startJobOrder(LocalDate.of(2020, 12, 02))
                .endJobOrder(LocalDate.of(2020, 12, 12))
                .price(35)
                .description("chanhed tire")
                .user(user)
                .build();
        jobOrderRepository.save(saveJobOrder1);
        jobOrderRepository.save(saveJobOrder2);

        //when - action or the behaviour that we are going test
        List<JobOrder> allJobOrder = jobOrderRepository.findAll();
        //then - verify the output
        Assertions.assertThat(allJobOrder).isNotNull();
        Assertions.assertThat(allJobOrder.size()).isEqualTo(2);
        Assertions.assertThat(allJobOrder.get(0).getPrice()).isNotSameAs(allJobOrder.get(1).getPrice());
    }

    //junit test for
    @DisplayName("Junit test for get JobOrder by DateJobOrder ")
    @Test
    public void givenJobOrderObject_whenFindByDateJobOrder_thenReturnJobOrderObject() {
        //given - precondition or setup
        jobOrderRepository.save(jobOrder);

        //when - action or the behaviour that we are going test
//        JobOrder DBjobOrder = jobOrderRepository.findByDateJobOrder(jobOrder.getStartJobOrder()).get();
//        //then - verify the output
//
//        Assertions.assertThat(DBjobOrder).isNotNull();
//        Assertions.assertThat(DBjobOrder.getStartJobOrder()).isEqualTo(LocalDate.of(2020, 12, 02));
    }

    @DisplayName("Junit test for get Job Order By Price  ")
    //junit test for
    @Test
    public void givenJobOrderObject_whenFindByPrice_thenReturnJobOrderObject() {
        //given - precondition or setup
        jobOrderRepository.save(jobOrder);
        //when - action or the behaviour that we are going test
        JobOrder DBjobOrder = jobOrderRepository.findByPrice(Integer.valueOf(12)).get();

        //then - verify the output
        Assertions.assertThat(DBjobOrder).isNotNull();
        Assertions.assertThat(DBjobOrder.getPrice()).isEqualTo(Integer.valueOf(12));
    }

    @DisplayName("Junit test for get JobOrder by id operation")
    //junit test for
    @Test
    public void givenJobOrderObject_whenFindById_thenReturnJobOrderObject() {
        //given - precondition or setup
        jobOrderRepository.save(jobOrder);
        //when - action or the behaviour that we are going test
        JobOrder DBjobOrder = jobOrderRepository.findById(jobOrder.getId()).get();

        //then - verify the output
        Assertions.assertThat(DBjobOrder).isNotNull();
        Assertions.assertThat(DBjobOrder.getUser().getUserRole()).isEqualTo(UserRole.USER);
        Assertions.assertThat(DBjobOrder.getId()).isGreaterThan(0);
    }

    @DisplayName("Junit test for update JobOrder operation")
    //junit test for
    @Test
    public void givenJobOrderObject_whenUpdate_thenReturnUpdateJobOrder() {
        //given - precondition or setup
        jobOrderRepository.save(jobOrder);
        //when - action or the behaviour that we are going test
        JobOrder savedJobOrder = jobOrderRepository.findById(jobOrder.getId()).get();
        savedJobOrder.setStartJobOrder(LocalDate.of(2023, 02, 07));
        savedJobOrder.setPrice(50);
        JobOrder updateJobOrder = jobOrderRepository.save(savedJobOrder);
        //then - verify the output
        Assertions.assertThat(updateJobOrder.getStartJobOrder()).isEqualTo(LocalDate.of(2023, 02, 07));
        Assertions.assertThat(updateJobOrder.getPrice()).isEqualTo(Integer.valueOf(50));
    }

    @DisplayName("Junit test for delete JobOrder operation")
    //junit test for
    @Test
    public void givenJobOrderObject_whenDelete_thenRemoweJobOrder() {
        //given - precondition or setup
        jobOrderRepository.save(jobOrder);
        //when - action or the behaviour that we are going test
        jobOrderRepository.deleteById(jobOrder.getId());
        Optional<JobOrder> jobOrderOptional = jobOrderRepository.findById(jobOrder.getId());
        //then - verify the output
        Assertions.assertThat(jobOrderOptional).isEmpty();
    }

    //junit test for
    @DisplayName("Junit test for custom query using JPQL Named params for specific Query  Price And Date")
    @Test
    public void givenPriceAndDate_whenfindByJPQLPriceAndDateOrder_thenReturnListJobOrders() {
        //given - precondition or setup
        jobOrderRepository.save(jobOrder);
        Integer price = Integer.valueOf(12);
        LocalDate startJobOrder = LocalDate.of(2020, 12, 02);
        LocalDate endJobOrder = LocalDate.of(2020, 12, 12);

  //      when - action or the behaviour that we are going test
        Optional<JobOrder> byUserNameList = jobOrderRepository.findByJPQLPriceAndDateOrder(price, startJobOrder);
     //   then - verify the output
        Assertions.assertThat(byUserNameList).isNotEmpty();
        Assertions.assertThat(byUserNameList.stream().count()).isEqualTo(1);
    }

    @DisplayName("Junit test for custom query usnig JPQL Named params for specific Query NIP and UserName  ")
    //junit test for
    @Test
    public void givenNIPAndUserName_whenfindByJPQLNIPAndUserName_thenReturnListJobOrders() {
        //given - precondition or setup

        User user = User.builder()
                .userName("John")
                .password("password")
                .email("kowalski@gmail.com")
                .firstName("Jan")
                .lastName("Kowalski")
                .ordersList(List.of())
                .build();
        JobOrder DBjobOrder = JobOrder.builder()
                .startJobOrder(LocalDate.of(2020, 12, 02))
                .endJobOrder(LocalDate.of(2020, 12, 15))
                .price(50)
                .user(user)
                .description("change tire")
                .build();

        jobOrderRepository.save(DBjobOrder);
        String NIP = "123456789";
        String userName = "John";

    }

}