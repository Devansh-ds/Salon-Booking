package com.devansh.images;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageCategoryDataRepository extends JpaRepository<ImageCategoryData, Integer> {

    Optional<ImageCategoryData> findByName(String name);

}
