package com.ej_ecommerce.payments.repository;

import com.ej_ecommerce.payments.model.Payment;
import com.ej_ecommerce.payments.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    public List<Payment> findAllByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    public List<Payment> findAllByIdUser(Long idUser);
    public List<Payment> findAllByStatus(Status status);
    public List<Payment> findAllByTotalBetween(Double minPrice, Double maxPrice);
}
