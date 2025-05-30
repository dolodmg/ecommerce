package com.ej_ecommerce.carts.repository;

import com.ej_ecommerce.carts.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
