package com.example.e_commerce.service;

import com.example.e_commerce.model.Category;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.CategoryRepo;
import com.example.e_commerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepo productRepo;
    @Autowired
    private final CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    public Product saveProduct(Product product) {
        if (product.getCategory() == null || product.getCategory().getName() == null) {
            throw new IllegalArgumentException("Category must not be null");
        }

        Category category = categoryRepo.findByName(product.getCategory().getName());

        if (category == null) {
            throw new IllegalArgumentException("Category '" + product.getCategory().getName() + "' is not valid.");
        }

        product.setCategory(category);
        return productRepo.save(product);
    }

    public void deleteProductById(Long id) {
        if (!productRepo.existsById(id)) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        productRepo.deleteById(id);
    }

    public List<Product> getProductByCategory(String category) {
        Category type = categoryRepo.findByName(category);
        System.out.printf(category,type);
        
        if (type == null) {
            throw new RuntimeException("No category found with name: " + category);
        }
    
        List<Product> products = productRepo.findByCategory(type);
        
        if (products == null || products.isEmpty()) {
            throw new RuntimeException("No products found in category: " + category);
        }
    
        return products;
    }
    
}
