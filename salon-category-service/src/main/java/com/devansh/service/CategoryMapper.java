package com.devansh.service;

import com.devansh.dto.CategoryDto;
import com.devansh.dto.ImageCategoryResponse;
import com.devansh.entity.Category;
import com.devansh.images.ImageCategoryData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMapper {


    public CategoryDto toCategoryDto(Category savedCategory) {

        ImageCategoryData images = savedCategory.getImages();

        String url = "http://localhost:8083/images/" + images.getName();
        ImageCategoryResponse imageCategoryResponse = new ImageCategoryResponse(url);

        return CategoryDto
                .builder()
                .name(savedCategory.getName())
                .images(imageCategoryResponse)
                .salonId(savedCategory.getSalonId())
                .build();
    }
}
