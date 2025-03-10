package com.devansh.service;

import com.devansh.dto.CategoryDto;
import com.devansh.dto.CreateCategoryRequest;
import com.devansh.dto.ImageCategoryResponse;
import com.devansh.entity.Category;
import com.devansh.images.ImageCategoryData;
import com.devansh.images.ImageService;
import com.devansh.repo.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ImageService imageService;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CreateCategoryRequest request, Integer salonId) throws IOException {
        String categoryName = request.categoryName();
        MultipartFile files = request.images();

        ImageCategoryData images = imageService.uploadImage(files);

        Category category = Category
                .builder()
                .name(categoryName)
                .images(images)
                .salonId(salonId)
                .build();
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategoryBySalon(Integer salonId) {

        List<Category> allCategories = categoryRepository.findAllBySalonId(salonId);

        return allCategories
                .stream()
                .map(categoryMapper::toCategoryDto).toList();
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {

        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {

        categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("no category to delete with id: " + categoryId));

        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDto updateCategory(Integer categoryId, CreateCategoryRequest request) throws IOException {

        Category oldCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("Category not found wiht id : " + categoryId));

        ImageCategoryData oldImage = oldCategory.getImages();
        ImageCategoryData newImage = imageService.updateImage(oldImage, request.images());

        oldCategory.setImages(newImage);
        oldCategory.setName(request.categoryName());

        Category savedCategory = categoryRepository.save(oldCategory);

        return categoryMapper.toCategoryDto(savedCategory);


    }
}














