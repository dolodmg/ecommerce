package com.ej_ecommerce.carts.mapper;

import com.ej_ecommerce.carts.dto.request.CartRequestDTO;
import com.ej_ecommerce.carts.dto.response.CartResponseDTO;
import com.ej_ecommerce.carts.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "idCart", ignore = true)
    Cart toEntity(CartRequestDTO cartDTO);

    CartResponseDTO toDto(Cart cart);
}
