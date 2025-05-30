package com.ej_ecommerce.carts.service;

import com.ej_ecommerce.carts.dto.request.CartItemRequestDTO;
import com.ej_ecommerce.carts.dto.request.CartRequestDTO;
import com.ej_ecommerce.carts.dto.response.CartResponseDTO;

import java.util.List;

public interface iCartService {
    public CartResponseDTO getCart(Long idCart);
    public List<CartResponseDTO> getAll();
    public CartResponseDTO getCartByUserId(Long idUser);
    public CartResponseDTO createCart(CartRequestDTO cartDTO);
    public CartResponseDTO addItemToCart(Long idCart, CartItemRequestDTO itemDTO);
    public CartResponseDTO removeItemFromCart(Long idCart, Long idProduct);
    public CartResponseDTO clearCart(Long idCart);
    public CartResponseDTO editCart(Long idCart, CartRequestDTO cartDTO);
    public String deleteCart(Long idCart);
}
