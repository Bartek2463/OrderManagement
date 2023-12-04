package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.security.CustomUserDetailsService;
import com.example.ordermanagement.security.jwt.JwtAuthorizationFilter;
import com.example.ordermanagement.service.AutheticationService;
import com.example.ordermanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(OwnerUserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    @MockBean
    private AutheticationService autheticationService;
    @MockBean
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //junit test for
    @Test
    public void given_when_then() throws Exception {
        //given - precondition or setup


        User user = new User();
        user.setEmail("email")
                .setUserName("username")
                .setFirstName("Jan")
                .setLastName("Kowalski")
                .setPassword("password");


        BDDMockito.given(userService.saveUser(user))
                .willAnswer((invocation) -> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/register"));
//
//
//         response.andDo(MockMvcResultHandlers.print());
    }


}
