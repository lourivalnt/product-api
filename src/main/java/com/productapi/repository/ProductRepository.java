package com.productapi.repository;

import com.productapi.domain.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    Product findById(Long id);
    List<Product> findAll(int page, int size);
    void delete(Long id);

    int countAll();
}
