package com.productapi.repository.impl;

import com.productapi.domain.Category;
import com.productapi.domain.Product;
import com.productapi.repository.ProductRepository;
import com.productapi.repository.mapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
           String sql = "INSERT INTO product (name, description, price, category) VALUES (?, ?, ?, ?)";
           jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getCategory_id().getId());
           Long id  = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
           product.setId(id);
        } else {
            String sql = "UPDATE products SET nome = ?, description = ?, price = ?, category_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getCategory_id().getId());
        }

        return product;
    }

    @Override
    public Product findById(Long id) {
        String sql = "SELECT p.id AS product_id, p.name AS product_name, p.description AS product_description, " +
                "p.price AS product_price, c.id AS category_id, c.name AS category_name " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());

    }

    @Override
    public List<Product> findAll(int page, int size) {
        String sql = "SELECT p.id AS id, p.name AS name, p.description AS description, " +
                "p.price AS price, c.id AS id, c.name AS name " +
                "FROM products p " +
                "JOIN categories c ON p.id = c.id" +
                "ORDER BY p.id " +
                "LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{size, page * size}, new ProductRowMapper());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM products";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
