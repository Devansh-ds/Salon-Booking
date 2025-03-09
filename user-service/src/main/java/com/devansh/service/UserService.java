package com.devansh.service;

import com.devansh.dto.UserRequest;
import com.devansh.dto.UserResponse;
import com.devansh.model.User;
import com.devansh.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserResponse createUser(@Valid UserRequest userRequest) {
        logger.info("Creating new user");

        User user = userMapper.toUser(userRequest);
        User savedUser = userRepository.save(user);

        logger.info("User created with id {}", savedUser.getId());

        return userMapper.toUserResponse(savedUser);

    }


    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(userMapper::toUserResponse)
                .toList();

    }

    public UserResponse updateUser(@Valid UserRequest userRequest, Integer userId) {
        User user = userMapper.toUser(userRequest);
        User oldUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user with id : " + userId + " not found"));

        logger.info("Updating user with id {}", userId);

        if(user.getFullName() != null) {
            oldUser.setFullName(user.getFullName());
        }
        if(user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getPhone() != null) {
            oldUser.setPhone(user.getPhone());
        }
        oldUser.setUpdatedAt(LocalDateTime.now());
        User updatedUser = userRepository.save(oldUser);

        logger.info("User updated with id {}", updatedUser.getId());

        return userMapper.toUserResponse(updatedUser);
    }

    public UserResponse getUserById(Integer userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user with id : " + userId + " not found"));
        return userMapper.toUserResponse(user);
    }

    public void deleteUserById(Integer userId) {
        getUserById(userId);
        userRepository.deleteById(userId);
        logger.info("User deleted with id {}", userId);
    }
}


















