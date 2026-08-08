package com.portfolio.BlogManagementSystem;

import com.portfolio.BlogManagementSystem.dtos.CreateUserDto;
import com.portfolio.BlogManagementSystem.dtos.UserResponseDto;
import com.portfolio.BlogManagementSystem.enums.Role;
import com.portfolio.BlogManagementSystem.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    void createUserTest() {
        CreateUserDto createUserDto = new CreateUserDto(
                "hunt@gmail.com",
                passwordEncoder().encode("12345678"),
                "ethan852",
                Role.ADMIN);
        UserResponseDto user = userService.createUser(createUserDto);
        System.out.println(user);
    }

    @Test
    void findAllUsersTest() {
        List<UserResponseDto> allUsers = userService.findAllUsers();
        for (UserResponseDto user : allUsers) {
            System.out.println(user);
        }
    }

    @Test
    void findUserByIdTest() {
        UserResponseDto userById = userService.findUserById(3L);
        System.out.println(userById);
    }

    @Test
    void deleteUserTest() {
        Boolean deleteUser = userService.deleteUser(4L);
        if (deleteUser) {
            System.out.println("User deleted successfully");
        }
    }


}
