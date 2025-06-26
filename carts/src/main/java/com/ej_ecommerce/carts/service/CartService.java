package com.ej_ecommerce.carts.service;

import com.ej_ecommerce.carts.dto.request.CartItemRequestDTO;
import com.ej_ecommerce.carts.dto.request.CartRequestDTO;
import com.ej_ecommerce.carts.dto.response.CartResponseDTO;
import com.ej_ecommerce.carts.dto.response.ProductResponseDTO;
import com.ej_ecommerce.carts.dto.response.UserResponseDTO;
import com.ej_ecommerce.carts.mapper.CartItemMapper;
import com.ej_ecommerce.carts.mapper.CartMapper;
import com.ej_ecommerce.carts.model.Cart;
import com.ej_ecommerce.carts.model.CartItem;
import com.ej_ecommerce.carts.repository.CartRepository;
import com.ej_ecommerce.carts.client.feign.ProductAPIClient;
import com.ej_ecommerce.carts.client.feign.UserAPIClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements iCartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final UserAPIClient userAPIClient;
    private final ProductAPIClient productAPIClient;

    public CartService(CartRepository cartRepository,
                       CartMapper cartMapper,
                       CartItemMapper cartItemMapper,
                       @Qualifier("com.ej_ecommerce.carts.client.feign.UserAPIClient") UserAPIClient userAPIClient,
                       @Qualifier("com.ej_ecommerce.carts.client.feign.ProductAPIClient") ProductAPIClient productAPIClient) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
        this.userAPIClient = userAPIClient;
        this.productAPIClient = productAPIClient;
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
        try {
            UserResponseDTO user = userAPIClient.getUser(idUser);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el usuario");
        }
        Cart cart = cartRepository.findByIdUser(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito perteneciente al usuario " + idUser));
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDTO createCart(CartRequestDTO cartDTO) {
        Optional<Cart> existingCart = cartRepository.findByIdUser(cartDTO.getIdUser());
        if (existingCart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya tiene un carrito");
        }
        try {
            userAPIClient.getUser(cartDTO.getIdUser());
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
        }
        Cart cart = new Cart();
        cart.setActive(true);
        cart.setIdUser(cartDTO.getIdUser());
        cart.setItems(new ArrayList<>());
        cart = cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDTO addItemToCart(Long idCart, CartItemRequestDTO itemDTO) {
        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito con el ID " + idCart));
        ProductResponseDTO product = productAPIClient.getProduct(itemDTO.getIdProduct());
        if (product.getStock() < itemDTO.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente para el producto con ID " + itemDTO.getIdProduct());
        }
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
        try {
            productAPIClient.getProduct(idProduct);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró en la tienda un producto con ID " + idProduct);
        }
        boolean removed = cart.getItems().removeIf(item -> item.getIdProduct().equals(idProduct));
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El producto no se encuentra en el carrito");
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
    public String deleteCart(Long idCart) {
        Cart existing = cartRepository.findById(idCart)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un carrito con el ID " + idCart));
        existing.setActive(false);
        cartRepository.save(existing);
        return "El carrito se eliminó correctamente";
    }
}
