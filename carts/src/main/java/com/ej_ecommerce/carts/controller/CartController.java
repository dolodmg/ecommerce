package com.ej_ecommerce.carts.controller;


import com.ej_ecommerce.carts.dto.request.CartItemRequestDTO;
import com.ej_ecommerce.carts.dto.request.CartRequestDTO;
import com.ej_ecommerce.carts.dto.response.CartResponseDTO;
import com.ej_ecommerce.carts.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@Tag(name = "Carts", description = "Carts Endpoints")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{idCart}")
    @Operation(summary = "Retrieve a cart by its ID")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable Long idCart) {
        CartResponseDTO cart = cartService.getCart(idCart);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/all")
    @Operation(summary = "Retrieve all carts")
    public ResponseEntity<List<CartResponseDTO>> getAll() {
        List<CartResponseDTO> carts = cartService.getAll();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/cart-by-user/{idUser}")
    @Operation(summary = "Retrieve a cart by user ID")
    public ResponseEntity<CartResponseDTO> getCartByUserId(@PathVariable Long idUser) {
        CartResponseDTO cart = cartService.getCartByUserId(idUser);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    @Operation(summary = "Create a new cart")
    public ResponseEntity<CartResponseDTO> createCart(@RequestBody CartRequestDTO cartDTO) {
        CartResponseDTO cart = cartService.createCart(cartDTO);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add-item/{idCart}")
    @Operation(summary = "Add an item to the cart")
    public ResponseEntity<CartResponseDTO> addItemToCart(@PathVariable Long idCart, @RequestBody CartItemRequestDTO itemDTO) {
        CartResponseDTO cart = cartService.addItemToCart(idCart, itemDTO);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{idCart}/items/{idProduct}")
    // TODO: Chequear la lógica de esto: Elimina todos los items de ese producto en el carrito?
    @Operation(summary = "Remove an item from the cart")
    public ResponseEntity<CartResponseDTO> removeItemFromCart(@PathVariable Long idCart, @PathVariable Long idProduct) {
        CartResponseDTO cart = cartService.removeItemFromCart(idCart, idProduct);
        return ResponseEntity.ok(cart);
    }

    @PatchMapping("/clear/{idCart}")
    @Operation(summary = "Clear all items from the cart")
    public ResponseEntity<CartResponseDTO> clearCart(@PathVariable Long idCart) {
        CartResponseDTO cart = cartService.clearCart(idCart);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{idCart}")
    @Operation(summary = "Logically delete a cart by ID")
    public ResponseEntity<String> deleteCart(@PathVariable Long idCart) {
        String message = cartService.deleteCart(idCart);
        return ResponseEntity.ok(message);
    }

}