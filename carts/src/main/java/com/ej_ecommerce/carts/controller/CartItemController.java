package com.ej_ecommerce.carts.controller;


import com.ej_ecommerce.carts.dto.response.CartItemResponseDTO;
import com.ej_ecommerce.carts.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
@Tag(name = "Cart Items", description = "Cart Items Endpoints")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{idItem}")
    @Operation(summary = "Retrieve a cart item by its ID")
    public ResponseEntity<CartItemResponseDTO> getItem(@PathVariable Long idItem) {
        CartItemResponseDTO item = cartItemService.getItem(idItem);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/quantity/{idItem}")
    // TODO: Chequear si no necesita el id del carrito para hacer esta actualización.
    @Operation(summary = "Update the quantity of an item in a cart")
    public ResponseEntity<CartItemResponseDTO> updateQuantity(@PathVariable Long idItem, @RequestBody int quantity) {
        CartItemResponseDTO item = cartItemService.updateQuantity(idItem, quantity);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/cart/{idCart}/items")
    @Operation(summary = "Retrieve the items of a cart by its ID")
    public ResponseEntity<List<CartItemResponseDTO>> getItemsByCart(@PathVariable Long idCart) {
        List<CartItemResponseDTO> items = cartItemService.getItemsByCart(idCart);
        return ResponseEntity.ok(items);
    }
}
