package com.ej_ecommerce.carts.service;

import com.ej_ecommerce.carts.dto.response.CartItemResponseDTO;
import com.ej_ecommerce.carts.dto.response.ProductResponseDTO;
import com.ej_ecommerce.carts.mapper.CartItemMapper;
import com.ej_ecommerce.carts.model.Cart;
import com.ej_ecommerce.carts.model.CartItem;
import com.ej_ecommerce.carts.repository.CartItemRepository;
import com.ej_ecommerce.carts.repository.CartRepository;
import com.ej_ecommerce.carts.client.feign.ProductAPIClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CartItemService implements iCartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final ProductAPIClient productAPIClient;

    public CartItemService(CartItemRepository cartItemRepository,
                           CartRepository cartRepository,
                           CartItemMapper cartItemMapper,
                           @Qualifier("com.ej_ecommerce.carts.client.feign.ProductAPIClient") ProductAPIClient productAPIClient) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemMapper = cartItemMapper;
        this.productAPIClient = productAPIClient;
    }

    @Override
    public CartItemResponseDTO getItem(Long idItem) {
        CartItem cartItem = cartItemRepository.findById(idItem)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el item con ID " + idItem));
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public CartItemResponseDTO updateQuantity(Long idItem, int quantity) {
        CartItem existing = cartItemRepository.findById(idItem)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el item con ID " + idItem));
        ProductResponseDTO product;
        try {
            product = productAPIClient.getProduct(existing.getIdProduct());
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el producto asociado al item");
        }
        if (quantity > product.getStock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay suficiente stock para el producto con ID " + existing.getIdProduct());
        }
        existing.setQuantity(quantity);
        CartItem updated = cartItemRepository.save(existing);
        return cartItemMapper.toDto(updated);
    }

    @Override
    public List<CartItemResponseDTO> getItemsByCart(Long idCart) {
        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el carrito con ID " + idCart));
        List<CartItem> items = cart.getItems();
        return items.stream()
                .map(cartItemMapper::toDto)
                .toList();
    }
}
