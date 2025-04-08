package com.productapi.mapper;

import com.productapi.domain.Product;
import com.productapi.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Habilita a integração com o Spring
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "price", source = "price", numberFormat = "#.00")
    ProductDTO toDTO(Product product);

    @Mapping(target = "price", source = "price", numberFormat = "#.00")
    Product toEntity(ProductDTO dto);

}
