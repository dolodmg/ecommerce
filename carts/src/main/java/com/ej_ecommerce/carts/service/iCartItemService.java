package com.ej_ecommerce.carts.service;

import com.ej_ecommerce.carts.dto.response.CartItemResponseDTO;

import java.util.List;

public interface iCartItemService {
    CartItemResponseDTO getItem(Long idItem);
    CartItemResponseDTO updateQuantity(Long idItem, int quantity);
    List<CartItemResponseDTO> getItemsByCart(Long idCart);
}
