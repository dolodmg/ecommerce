package com.ej_ecommerce.carts.controller;


import com.ej_ecommerce.carts.dto.request.CartItemRequestDTO;
import com.ej_ecommerce.carts.dto.request.CartRequestDTO;
import com.ej_ecommerce.carts.dto.response.CartResponseDTO;
import com.ej_ecommerce.carts.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{idCart}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable Long idCart) {
        CartResponseDTO cart = cartService.getCart(idCart);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartResponseDTO>> getAll() {
        List<CartResponseDTO> carts = cartService.getAll();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/cart-by-user/{idUser}")
    public ResponseEntity<CartResponseDTO> getCartByUserId(@PathVariable Long idUser) {
        CartResponseDTO cart = cartService.getCartByUserId(idUser);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> createCart(@RequestBody CartRequestDTO cartDTO) {
        CartResponseDTO cart = cartService.createCart(cartDTO);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add-item/{idCart}")
    public ResponseEntity<CartResponseDTO> addItemToCart(@PathVariable Long idCart, @RequestBody CartItemRequestDTO itemDTO) {
        CartResponseDTO cart = cartService.addItemToCart(idCart, itemDTO);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{idCart}/items/{idProduct}")
    public ResponseEntity<CartResponseDTO> removeItemFromCart(@PathVariable Long idCart, @PathVariable Long idProduct) {
        CartResponseDTO cart = cartService.removeItemFromCart(idCart, idProduct);
        return ResponseEntity.ok(cart);
    }

    @PatchMapping("/clear/{idCart}")
    public ResponseEntity<CartResponseDTO> clearCart(@PathVariable Long idCart) {
        CartResponseDTO cart = cartService.clearCart(idCart);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{idCart}")
    public ResponseEntity<CartResponseDTO> editCart(@PathVariable Long idCart, @RequestBody CartRequestDTO cartDTO) {
        CartResponseDTO cart = cartService.editCart(idCart, cartDTO);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{idCart}")
    public ResponseEntity<String> deleteCart(@PathVariable Long idCart) {
        String message = cartService.deleteCart(idCart);
        return ResponseEntity.ok(message);
    }

}