package com.devansh.controller;

import com.devansh.dto.UserRequest;
import com.devansh.dto.UserResponse;
import com.devansh.model.User;
import com.devansh.repo.UserRepository;
import com.devansh.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid  UserRequest userRequest) {
        return new ResponseEntity<>(
                userService.createUser(userRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer userId,
            @RequestBody @Valid UserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.updateUser(userRequest, userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User with id: " + userId + " deleted successfully");
    }



}
























