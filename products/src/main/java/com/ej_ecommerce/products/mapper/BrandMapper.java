package com.ej_ecommerce.products.mapper;

import com.ej_ecommerce.products.dto.request.BrandRequestDTO;
import com.ej_ecommerce.products.dto.response.BrandResponseDTO;
import com.ej_ecommerce.products.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @Mapping(target = "idBrand", ignore = true)
    Brand toEntity(BrandRequestDTO dto);

    BrandResponseDTO toDto(Brand brand);
}
