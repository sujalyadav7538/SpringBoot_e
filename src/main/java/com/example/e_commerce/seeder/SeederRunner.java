package com.example.e_commerce.seeder;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


import com.example.e_commerce.model.Category;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.CategoryRepo;
import com.example.e_commerce.repository.ProductRepo;

public class SeederRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SeederConfig.class);

        CategoryRepo categoryRepo = context.getBean(CategoryRepo.class);
        ProductRepo productRepo = context.getBean(ProductRepo.class);

        // === Empty Database First ===

        categoryRepo.deleteAll();
        productRepo.deleteAll();

        // === Create Categories ===
        Category electronics = new Category("Electronics");
        Category fashion = new Category("Fashion");
        Category books = new Category("Books");
        Category home = new Category("Home & Kitchen");

        categoryRepo.save(electronics);
        categoryRepo.save(fashion);
        categoryRepo.save(books);
        categoryRepo.save(home);

        // === Create Products ===
        productRepo.save(new Product("Phone", "Android smartphone", electronics));
        productRepo.save(new Product("Laptop", "Gaming laptop with RTX 3060", electronics));
        productRepo.save(new Product("Headphones", "Noise-cancelling over-ear headphones", electronics));
        productRepo.save(new Product("Smartwatch", "Fitness tracker and smart watch", electronics));

        productRepo.save(new Product("T-Shirt", "100% cotton casual wear", fashion));
        productRepo.save(new Product("Jeans", "Slim-fit blue denim", fashion));
        productRepo.save(new Product("Sneakers", "Unisex running shoes", fashion));

        productRepo.save(new Product("Cookbook", "100 recipes from around the world", books));
        productRepo.save(new Product("Novel", "Bestselling mystery thriller", books));
        productRepo.save(new Product("Notebook", "200-page ruled notebook", books));

        productRepo.save(new Product("Blender", "High-speed kitchen blender", home));
        productRepo.save(new Product("Lamp", "LED study lamp", home));
        productRepo.save(new Product("Curtains", "Blackout window curtains", home));

        System.out.println("✅ Dummy data seeded successfully.");
        context.close();
    }
}
