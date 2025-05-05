package com.example.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.e_commerce.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    Category findByName(String name);
}
