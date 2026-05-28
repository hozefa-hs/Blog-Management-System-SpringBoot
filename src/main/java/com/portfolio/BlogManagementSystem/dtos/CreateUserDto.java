package com.portfolio.BlogManagementSystem.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String email;

    private String password;
}
