package com.portfolio.BlogManagementSystem.services.implementations;

import com.portfolio.BlogManagementSystem.dtos.CreateUserDto;
import com.portfolio.BlogManagementSystem.dtos.UserResponseDto;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.exceptions.ResourceNotFoundException;
import com.portfolio.BlogManagementSystem.repositories.UserRepository;
import com.portfolio.BlogManagementSystem.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(@Valid CreateUserDto createUserDto) {
        //TODO check if user is already present
        if (userRepository.findByUsername(createUserDto.getUsername()).isPresent()) {
            throw new ResourceNotFoundException("Username " + createUserDto.getUsername() + " already exists");
        }

        User newUser = new User();
        newUser.setUsername(createUserDto.getUsername());
        newUser.setEmail(createUserDto.getEmail());
        newUser.setRole(createUserDto.getRole());
        newUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        //User newUser = modelMapper.map(createUserDto, User.class);
        User savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public Page<UserResponseDto> findAllUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<User> userList = userRepository.findAll(pageable);
        return userList
                .map(user -> modelMapper.map(user, UserResponseDto.class));
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (id == null) return false;
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.deleteById(id);
        return true;
    }


}
