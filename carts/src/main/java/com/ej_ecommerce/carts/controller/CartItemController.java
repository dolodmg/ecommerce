package com.ej_ecommerce.carts.controller;


import com.ej_ecommerce.carts.dto.response.CartItemResponseDTO;
import com.ej_ecommerce.carts.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<CartItemResponseDTO> getItem(@PathVariable Long idItem) {
        CartItemResponseDTO item = cartItemService.getItem(idItem);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/quantity/{idItem}")
    public ResponseEntity<CartItemResponseDTO> updateQuantity(@PathVariable Long idItem, @RequestBody int quantity) {
        CartItemResponseDTO item = cartItemService.updateQuantity(idItem, quantity);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/cart/{idCart}/items")
    public ResponseEntity<List<CartItemResponseDTO>> getItemsByCart(@PathVariable Long idCart) {
        List<CartItemResponseDTO> items = cartItemService.getItemsByCart(idCart);
        return ResponseEntity.ok(items);
    }
}
