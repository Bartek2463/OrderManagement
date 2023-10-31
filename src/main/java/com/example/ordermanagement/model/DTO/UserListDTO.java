package com.example.ordermanagement.model.DTO;


import com.example.ordermanagement.model.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserListDTO {

    private Long id;
    private String firstName;
    private String lastName;


    public static UserListDTO mapToDto(User user){
        return new UserListDTO()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }

    public static User mapToModel(UserListDTO dto) {
        return new User()
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName());

    }

    public static List<UserListDTO> mapToDto(List<User> users) {
        return users.stream()
                .map(UserListDTO::mapToDto)
                .collect(Collectors.toList());
    }





}
