package com.productapi.repository.impl;

import com.productapi.domain.Category;
import com.productapi.domain.Product;
import com.productapi.repository.ProductRepository;
import com.productapi.repository.mapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @CacheEvict(value = "productsPage", allEntries = true) // Limpa todo o cache de páginas
    public Product save(Product product) {
        if (product.getId() == null) {
           String sql = "INSERT INTO product (name, description, price, category) VALUES (?, ?, ?, ?)";
           jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getCategory_id().getId());
           Long id  = jdbcTemplate.queryForObject("SELECT currval('id')", Long.class);
           product.setId(id);
        } else {
            String sql = "UPDATE products SET nome = ?, description = ?, price = ?, category_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getCategory_id().getId());
        }

        return product;
    }

    @Override
    public Product findById(Long id) {
        String sql = "SELECT p.id, p.name, p.description, " +
                "p.price, c.id, c.name " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);

    }

    @Override
    @Cacheable(value = "productsPage", key = "{#page, #size}") // Chave composta: page + size
    public List<Product> findAll(int page, int size) {
        System.out.println("Buscando produtos no banco de dados...");
        String sql = "SELECT p.id AS id, p.name AS name, p.description AS description, " +
                "p.price AS price, c.id AS id, c.name AS name " +
                "FROM products p " +
                "JOIN categories c ON p.id = c.id " +
                "ORDER BY p.id " +
                "LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new ProductRowMapper(), size, page * size);
    }

    @Override
    @CacheEvict(value = "productsPage", allEntries = true)
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM products";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        return Optional.ofNullable(result).orElse(0);
    }

    public boolean existsById(Long id) {
        String sql = "SELECT count(*) FROM products WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
