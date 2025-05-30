package com.ej_ecommerce.products.mapper;

import com.ej_ecommerce.products.dto.request.BoardGameRequestDTO;
import com.ej_ecommerce.products.dto.response.BoardGameResponseDTO;
import com.ej_ecommerce.products.model.BoardGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BoardGameMapper {
    @Mappings({
            @Mapping(target = "idBoardGame", ignore = true),
            @Mapping(source = "idBrand", target = "brand.idBrand")
    })
    BoardGame toEntity(BoardGameRequestDTO dto);

    @Mapping(source = "brand", target = "brand")
    BoardGameResponseDTO toDto(BoardGame boardGame);
}
