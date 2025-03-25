package com.productapi.repository;

import com.productapi.domain.Product;

import java.util.List;

public interface ProductRepository {
    Product salvar(Product product);
    Product findById(Long id);
    List<Product> findAll();
    void excluir(Long id);
}
