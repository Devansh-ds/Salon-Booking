package com.devansh.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryDto(
        String name,
        Integer salonId,
        ImageCategoryResponse images
) {
}
