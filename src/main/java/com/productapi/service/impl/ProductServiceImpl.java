package com.productapi.service.impl;

import com.productapi.domain.Product;
import com.productapi.repository.ProductRepository;
import com.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll(int page, int size) {
        return productRepository.findAll(page, size);
    }

    @Override
    public void delete(Long id) {
        if(!productRepository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + "not found");
        }
        productRepository.delete(id);
    }

    @Override
    public int countAll() {
        return productRepository.countAll();
    }
}
