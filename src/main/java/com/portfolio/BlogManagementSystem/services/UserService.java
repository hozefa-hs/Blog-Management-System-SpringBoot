package com.portfolio.BlogManagementSystem.services;

import com.portfolio.BlogManagementSystem.dtos.CreateUserDto;
import com.portfolio.BlogManagementSystem.dtos.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponseDto createUser(CreateUserDto createUserDto);

    List<UserResponseDto> findAllUsers();

    UserResponseDto findUserById(Long id);

    Boolean deleteUser(Long id);
}
