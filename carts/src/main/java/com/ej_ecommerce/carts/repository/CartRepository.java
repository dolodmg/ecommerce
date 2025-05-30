package com.ej_ecommerce.carts.repository;

import com.ej_ecommerce.carts.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByIdUser(Long idUser);
}
