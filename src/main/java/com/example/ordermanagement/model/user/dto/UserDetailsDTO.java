package com.example.ordermanagement.model.user.dto;

import com.example.ordermanagement.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserDetailsDTO {


    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;


    public static UserDetailsDTO mapToDto(User user){
        return new UserDetailsDTO()
                .setUserName(user.getUserName())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPassword(user.getPassword());
    }

    public static User mapToModel(UserDetailsDTO detailsDto) {
        return new User()
                .setUserName(detailsDto.getUserName())
                .setEmail(detailsDto.getEmail())
                .setFirstName(detailsDto.getFirstName())
                .setLastName(detailsDto.getLastName())
                .setPassword(detailsDto.getPassword());
    }
}
