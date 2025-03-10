package com.devansh.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record SalonUpdationDto (
        String salonRequestDto,
        List<MultipartFile> files
) {
}
