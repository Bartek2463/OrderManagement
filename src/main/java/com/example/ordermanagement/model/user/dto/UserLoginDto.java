package com.example.ordermanagement.model.user.dto;

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
}
