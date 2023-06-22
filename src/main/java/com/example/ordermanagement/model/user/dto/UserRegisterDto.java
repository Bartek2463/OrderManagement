package com.example.ordermanagement.model.user.dto;

import com.example.ordermanagement.model.user.User;
import com.example.ordermanagement.model.user.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserRegisterDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;

    public static UserRegisterDto mapToDto(User user) {
        return new UserRegisterDto()
                .setId(user.getId())
                .setUsername(user.getUserName())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setRole(user.getRole());
    }
}




