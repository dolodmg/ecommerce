package com.ej_ecommerce.products.mapper;

import com.ej_ecommerce.products.dto.request.ProductRequestDTO;
import com.ej_ecommerce.products.dto.response.ProductResponseDTO;
import com.ej_ecommerce.products.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "idProduct", ignore = true)
    Product toEntity(ProductRequestDTO dto);
    ProductResponseDTO toDto(Product product);
}

