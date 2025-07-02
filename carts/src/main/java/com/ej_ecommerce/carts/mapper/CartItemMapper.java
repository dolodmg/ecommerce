package com.ej_ecommerce.carts.mapper;


import com.ej_ecommerce.carts.dto.request.CartItemRequestDTO;
import com.ej_ecommerce.carts.dto.response.CartItemResponseDTO;
import com.ej_ecommerce.carts.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(target = "idItem", ignore = true)
    @Mapping(target = "cart", ignore = true)
    CartItem toEntity(CartItemRequestDTO cartItemRequestDTO);

    CartItemResponseDTO toDto(CartItem cartItem);
}
