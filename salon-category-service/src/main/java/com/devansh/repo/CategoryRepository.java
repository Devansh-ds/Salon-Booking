package com.devansh.repo;

import com.devansh.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllBySalonId(Integer salonId);

}
