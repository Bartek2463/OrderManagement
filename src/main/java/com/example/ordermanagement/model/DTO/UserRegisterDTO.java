package com.example.ordermanagement.model.DTO;

import com.example.ordermanagement.model.Users;
import com.example.ordermanagement.model.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserRegisterDTO {

    private Long  id;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;

    public static UserRegisterDTO mapToDto (Users user){
        return new UserRegisterDTO()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setRole(user.getUserRole());
    }
}
