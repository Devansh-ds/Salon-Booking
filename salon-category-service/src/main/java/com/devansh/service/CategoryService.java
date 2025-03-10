package com.devansh.service;

import com.devansh.dto.CategoryDto;
import com.devansh.dto.CreateCategoryRequest;
import com.devansh.entity.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CreateCategoryRequest request, Integer salonId) throws IOException;

    List<CategoryDto> getAllCategoryBySalon(Integer salonId);

    CategoryDto getCategoryById(Integer categoryId);

    void deleteCategoryById(Integer categoryId);

    CategoryDto updateCategory(Integer categoryId, CreateCategoryRequest request) throws IOException;
}
