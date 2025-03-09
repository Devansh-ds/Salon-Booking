package com.devansh.dto;

import com.devansh.model.Role;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserRequest(
        @NotNull(message = "name cannot be null")
        @NotBlank(message = "name cannot be blank")
        @NotEmpty(message = "name cannot be empty")
        String fullName,
        @Email(message = "Email should be valid")
        String email,
        @Positive(message = "phone no. should be positive")
        String phone,

        Role role,
        @NotEmpty(message = "password cannot be empty")
        @NotNull(message = "password cannot be null")
        String password

) {
}
