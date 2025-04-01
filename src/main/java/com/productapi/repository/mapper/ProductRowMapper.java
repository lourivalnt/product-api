package com.productapi.repository.mapper;

import com.productapi.domain.Category;
import com.productapi.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .category_id(Category.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build())
                .build();
    }
}
