package com.devansh.service;

import com.devansh.dto.UserRequest;
import com.devansh.dto.UserResponse;
import com.devansh.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserMapper {


    public User toUser(@Valid UserRequest userRequest) {
        User user = User
                .builder()
                .fullName(userRequest.fullName())
                .email(userRequest.email())
                .phone(userRequest.phone())
                .role(userRequest.role())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .password(userRequest.password())
                .build();
        return user;
    }

    public UserResponse toUserResponse(User savedUser) {
        UserResponse userResponse = UserResponse
                .builder()
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .role(savedUser.getRole())
                .build();
        return userResponse;
    }
}

























