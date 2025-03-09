package com.devansh.dto;

import com.devansh.image.ImageData;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@Builder
public record SalonResponseDto(
        String name,
        List<ImageResponse> images,
        String address,
        String email,
        String city,
        String openTime,
        String closeTime,
        Integer ownerId,
        String phone
) {
}
