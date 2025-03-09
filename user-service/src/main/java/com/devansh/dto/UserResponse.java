package com.devansh.dto;

import com.devansh.model.Role;
import lombok.Builder;

@Builder
public record UserResponse(
        String fullName,
        String email,
        String phone,
        Role role
) {
}
