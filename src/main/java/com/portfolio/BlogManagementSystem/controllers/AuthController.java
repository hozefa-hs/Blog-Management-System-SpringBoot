package com.portfolio.BlogManagementSystem.controllers;

import com.portfolio.BlogManagementSystem.dtos.AuthRequestDto;
import com.portfolio.BlogManagementSystem.dtos.CreateUserDto;
import com.portfolio.BlogManagementSystem.dtos.UserResponseDto;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.enums.Role;
import com.portfolio.BlogManagementSystem.exceptions.ResourceNotFoundException;
import com.portfolio.BlogManagementSystem.services.UserService;
import com.portfolio.BlogManagementSystem.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid CreateUserDto createUserDto) {
        createUserDto.setRole(Role.USER);
        UserResponseDto userResponseDto = userService.createUser(createUserDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    //You should have an admin in your database first.
    //because an admin can only create another admin.
    @PostMapping("/register-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> registerAdmin(@RequestBody @Valid CreateUserDto createUserDto) {
        createUserDto.setRole(Role.ADMIN);
        UserResponseDto userResponseDto = userService.createUser(createUserDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }


    @PostMapping("/authenticate")
    public String loginAndGenerateToken(@RequestBody @Valid AuthRequestDto authRequestDto) {

        try {

            //First Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDto.getUsername(),
                            authRequestDto.getPassword()));

            //generate JWT token
            return jwtUtil.generateToken(authRequestDto.getUsername());

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Login error : "+e);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto userById = userService.findUserById(id);
        return ResponseEntity.ok(userById);
    }

}
