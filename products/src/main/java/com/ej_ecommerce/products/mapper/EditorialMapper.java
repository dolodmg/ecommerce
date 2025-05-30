package com.ej_ecommerce.products.mapper;

import com.ej_ecommerce.products.dto.request.EditorialRequestDTO;
import com.ej_ecommerce.products.dto.response.EditorialResponseDTO;
import com.ej_ecommerce.products.model.Editorial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EditorialMapper {
    @Mapping(target = "idEditorial", ignore = true)
    Editorial toEntity(EditorialRequestDTO dto);

    EditorialResponseDTO toDto(Editorial editorial);
}
