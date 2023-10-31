package com.example.ordermanagement.security;


import com.example.ordermanagement.exception.ElementNotFoundException;
import com.example.ordermanagement.model.User;
import com.example.ordermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = userService.searchUserName(username)
                .orElseThrow(() -> new ElementNotFoundException("User", "username", username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(users.getUserRole().name()));

        return UserPrinciple.builder()
                .users(users)
                .id(users.getId())
                .userName(users.getUserName())
                .password(users.getPassword())
                .authorities(authorities)
                .build();
    }
}
