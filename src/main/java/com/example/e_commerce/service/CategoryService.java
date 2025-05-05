package com.example.e_commerce.service;

import com.example.e_commerce.model.Category;
import com.example.e_commerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategory(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be null or empty.");
        }

        Category category = categoryRepo.findByName(name);
        if (category == null) {
            throw new IllegalArgumentException("Category with name '" + name + "' not found.");
        }

        return category;
    }

    public Category saveCategory(Category category) {
        if (category == null || category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be null or empty.");
        }

        return categoryRepo.save(category);
    }

    public void deleteCategoryById(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
        }

        categoryRepo.deleteById(id);
    }
}
