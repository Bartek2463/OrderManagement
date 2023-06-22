package com.example.ordermanagement.model.user.dto;

import com.example.ordermanagement.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserLoginDto {
    private Long id;
    private String username;
    private String password;

    public static UserLoginDto mapToDto(User user){
        return new UserLoginDto()
                .setId(user.getId())
                .setUsername(user.getUserName())
                .setPassword(user.getPassword());
    }

    public static User mapToModel(UserLoginDto dto){
        return new User()
                .setId(dto.getId())
                .setUserName(dto.getUsername())
                .setPassword(dto.getPassword());
    }

}


