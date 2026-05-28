package com.portfolio.BlogManagementSystem;

import com.portfolio.BlogManagementSystem.dtos.CreateUserDto;
import com.portfolio.BlogManagementSystem.dtos.UserResponseDto;
import com.portfolio.BlogManagementSystem.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    UserService userService;

    @Test
    void createUserTest() {
        CreateUserDto createUserDto = new CreateUserDto("john@gmail.com", "12345678");
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
        UserResponseDto userById = userService.findUserById(2L);
        System.out.println(userById);
    }

    @Test
    void deleteUserTest() {
        Boolean deleteUser = userService.deleteUser(2L);
        if (deleteUser) {
            System.out.println("User deleted successfully");
        }
    }




}
