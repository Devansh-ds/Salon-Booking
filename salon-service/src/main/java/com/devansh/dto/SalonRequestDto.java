package com.devansh.dto;

import com.devansh.image.ImageData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalTime;
import java.util.List;

public record SalonRequestDto(
    String name,
    String address,
    String email,
    String phone,
    String city,
    String openTime,
    String closeTime
) {
}
