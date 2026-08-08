package com.portfolio.BlogManagementSystem.dtos;

import com.portfolio.BlogManagementSystem.enums.Role;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String email;

    private String password;

    private String username;

    private Role role;
}
