package com.ej_ecommerce.carts.service;

import com.ej_ecommerce.carts.dto.request.CartItemRequestDTO;
import com.ej_ecommerce.carts.dto.request.CartRequestDTO;
import com.ej_ecommerce.carts.dto.response.CartResponseDTO;

import java.util.List;

public interface iCartService {
    CartResponseDTO getCart(Long idCart);
    List<CartResponseDTO> getAll();
    CartResponseDTO getCartByUserId(Long idUser);
    CartResponseDTO createCart(CartRequestDTO cartDTO);
    CartResponseDTO addItemToCart(Long idCart, CartItemRequestDTO itemDTO);
    CartResponseDTO removeItemFromCart(Long idCart, Long idProduct);
    CartResponseDTO clearCart(Long idCart);
    String deleteCart(Long idCart);
}
