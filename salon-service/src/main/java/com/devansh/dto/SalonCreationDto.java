package com.devansh.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record SalonCreationDto(
        String salonRequestDto,
        String userDto,
        List<MultipartFile> files
) {
}
