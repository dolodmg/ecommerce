package com.ej_ecommerce.carts.service;

import com.ej_ecommerce.carts.dto.request.CartItemRequestDTO;
import com.ej_ecommerce.carts.dto.request.CartRequestDTO;
import com.ej_ecommerce.carts.dto.response.CartResponseDTO;
import com.ej_ecommerce.carts.mapper.CartItemMapper;
import com.ej_ecommerce.carts.mapper.CartMapper;
import com.ej_ecommerce.carts.model.Cart;
import com.ej_ecommerce.carts.model.CartItem;
import com.ej_ecommerce.carts.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService implements iCartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    public CartService(CartRepository cartRepository, CartMapper cartMapper, CartItemMapper cartItemMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartResponseDTO getCart(Long idCart) {
        Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el carrito con ID " + idCart));
        return cartMapper.toDto(cart);
    }

    @Override
    public List<CartResponseDTO> getAll() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(cartMapper::toDto)
                .toList();
    }

    @Override
    public CartResponseDTO getCartByUserId(Long idUser) {
        Cart cart = cartRepository.findByIdUser(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito perteneciente al usuario " + idUser));
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDTO createCart(CartRequestDTO cartDTO) {
        Cart cart = cartMapper.toEntity(cartDTO);
        cart = cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDTO addItemToCart(Long idCart, CartItemRequestDTO itemDTO) {
        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito con el ID " + idCart));
        Optional<CartItem> existingItemOpt = cart.getItems().stream()
                .filter(item -> item.getIdProduct().equals(itemDTO.getIdProduct()))
                .findFirst();
        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + itemDTO.getQuantity());
        } else {
            CartItem newItem = cartItemMapper.toEntity(itemDTO);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }
        Cart updated = cartRepository.save(cart);
        return cartMapper.toDto(updated);
    }

    @Override
    public CartResponseDTO removeItemFromCart(Long idCart, Long idProduct) {
        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito con el id " + idCart));
        boolean removed = cart.getItems().removeIf(item -> item.getIdProduct().equals(idProduct));
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un producto con id " + idProduct);
        }
        Cart updated = cartRepository.save(cart);
        return cartMapper.toDto(updated);
    }

    @Override
    public CartResponseDTO clearCart(Long idCart) {
        Cart existing = cartRepository.findById(idCart)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito con el ID " + idCart));
        existing.getItems().clear();
        Cart updated = cartRepository.save(existing);
        return cartMapper.toDto(updated);
    }

    @Override
    public CartResponseDTO editCart(Long idCart, CartRequestDTO cartDTO) {
        Cart existing = cartRepository.findById(idCart)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito con el ID " + idCart));
        List<CartItem> items = cartDTO.getItems().stream()
                .map(dto -> {
                    CartItem item = cartItemMapper.toEntity(dto);
                    item.setCart(existing);
                    return item;
                })
                .collect(Collectors.toList());
        existing.setItems(items);
        Cart updated = cartRepository.save(existing);
        return cartMapper.toDto(updated);
    }

    @Override
    public String deleteCart(Long idCart) {
        Cart existing = cartRepository.findById(idCart)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito con el ID " + idCart));
        existing.setActive(false);
        cartRepository.save(existing);
        return "El carrito se eliminó correctamente";
    }
}
