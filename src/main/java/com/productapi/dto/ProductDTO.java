package com.productapi.dto;

import com.productapi.domain.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO {

    @NotBlank(message = "The name is required")
    private String name;

    private String description;

    @NotNull(message = "The price is required")
    @DecimalMin(value = "0.01", message = "The price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "The category is required")
    private Category category_id;
}
