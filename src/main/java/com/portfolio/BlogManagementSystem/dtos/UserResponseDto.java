package com.portfolio.BlogManagementSystem.dtos;

import com.portfolio.BlogManagementSystem.enums.Role;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String email;
    private String password;
    private Role role;
}
