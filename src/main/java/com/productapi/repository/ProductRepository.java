package com.productapi.repository;

import com.productapi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll(int page, int size);
    void delete(Long id);
    int countAll();
    boolean existsById(Long id);
}
