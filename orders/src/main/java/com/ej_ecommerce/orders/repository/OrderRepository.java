package com.ej_ecommerce.orders.repository;

import com.ej_ecommerce.orders.model.Order;
import com.ej_ecommerce.orders.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByIdUser(Long idUser);
    List<Order> findOrderByStatus(Status status);
    List<Order> findAllByOrderDateBetween(LocalDate start, LocalDate end);
}
