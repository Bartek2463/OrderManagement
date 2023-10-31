package com.example.ordermanagement.model.DTO;

import com.example.ordermanagement.model.User;
import com.example.ordermanagement.model.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserLoginDTO {

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;

    public static UserLoginDTO mapToDto(User user){
        return new UserLoginDTO()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setPassword(user.getPassword())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setRole(user.getUserRole());
    }
    public static User mapToModel(UserLoginDTO userLoginDTO){
        return new User()
                .setId(userLoginDTO.getId())
                .setUserName(userLoginDTO.getUserName())
                .setPassword(userLoginDTO.getPassword())
                .setFirstName(userLoginDTO.getFirstName())
                .setLastName(userLoginDTO.getLastName())
                .setUserRole(userLoginDTO.getRole());

    }

}
