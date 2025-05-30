package com.ej_ecommerce.carts.service;

import com.ej_ecommerce.carts.dto.response.CartItemResponseDTO;

import java.util.List;

public interface iCartItemService {
    public CartItemResponseDTO getItem(Long idItem);
    public CartItemResponseDTO updateQuantity(Long idItem, int quantity);
    public List<CartItemResponseDTO> getItemsByCart(Long idCart);
}
