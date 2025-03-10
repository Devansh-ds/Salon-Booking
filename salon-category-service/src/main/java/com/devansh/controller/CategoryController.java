package com.devansh.controller;

import com.devansh.dto.CategoryDto;
import com.devansh.dto.CreateCategoryRequest;
import com.devansh.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/{salonId}")
    public ResponseEntity<CategoryDto> createCategory(
            @ModelAttribute CreateCategoryRequest request,
            @PathVariable Integer salonId
            ) throws IOException {
        return ResponseEntity.ok(categoryService.createCategory(request,salonId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @ModelAttribute CreateCategoryRequest request,
            @PathVariable Integer categoryId
    ) throws IOException {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, request));
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<CategoryDto>> findCategoriesBySalonId(@PathVariable Integer salonId) throws IOException {
        return ResponseEntity.ok(categoryService.getAllCategoryBySalon(salonId));
    }

    @GetMapping("/{catgoryId}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Integer catgoryId) throws IOException {
        return ResponseEntity.ok(categoryService.getCategoryById(catgoryId));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Integer categoryId) throws IOException {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok("Deleted Category successfully.");
    }


}



















