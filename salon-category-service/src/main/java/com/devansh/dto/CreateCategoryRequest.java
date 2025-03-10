package com.devansh.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CreateCategoryRequest(
        MultipartFile images,
        String categoryName
) {
}
