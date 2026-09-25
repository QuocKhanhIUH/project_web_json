package com.example.app.service;

import com.example.app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    List<Product> search(String keyword);
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    long count();
    List<Product> findTop5Recent();
}
