package com.portfolio.BlogManagementSystem.controllers;

import com.portfolio.BlogManagementSystem.dtos.AuthRequestDto;
import com.portfolio.BlogManagementSystem.dtos.CreateUserDto;
import com.portfolio.BlogManagementSystem.dtos.UserResponseDto;
import com.portfolio.BlogManagementSystem.entities.User;
import com.portfolio.BlogManagementSystem.enums.Role;
import com.portfolio.BlogManagementSystem.services.UserService;
import com.portfolio.BlogManagementSystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody CreateUserDto createUserDto) {
        createUserDto.setRole(Role.USER);
        UserResponseDto userResponseDto = userService.createUser(createUserDto);
        return ResponseEntity.ok(userResponseDto);
    }

    //You should have an admin in your database first.
    //because an admin can only create another admin.
    @PostMapping("/register-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> registerAdmin(@RequestBody CreateUserDto createUserDto) {
        createUserDto.setRole(Role.ADMIN);
        UserResponseDto userResponseDto = userService.createUser(createUserDto);
        return ResponseEntity.ok(userResponseDto);
    }


    @PostMapping("/authenticate")
    public String loginAndGenerateToken(@RequestBody AuthRequestDto authRequestDto) {

        try {

            //First Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDto.getUsername(),
                            authRequestDto.getPassword()));

            //generate JWT token
            return jwtUtil.generateToken(authRequestDto.getUsername());

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Boolean isDeleteUser = userService.deleteUser(id);
        return isDeleteUser ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
