package com.portfolio.BlogManagementSystem.services.implementations;

import com.portfolio.BlogManagementSystem.dtos.CreateUserDto;
import com.portfolio.BlogManagementSystem.dtos.UserResponseDto;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.repositories.UserRepository;
import com.portfolio.BlogManagementSystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        User newUser = modelMapper.map(createUserDto, User.class);
        User savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .toList();
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public Boolean deleteUser(Long id) {
        if(id == null) return false;
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
        userRepository.deleteById(id);
        return true;
    }


}
