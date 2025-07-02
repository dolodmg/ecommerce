package com.ej_ecommerce.payments.service;

import com.ej_ecommerce.payments.dto.request.PaymentRequestDTO;
import com.ej_ecommerce.payments.dto.response.PaymentResponseDTO;
import com.ej_ecommerce.payments.model.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface iPaymentService {
    PaymentResponseDTO getPayment(Long idPayment);
    List<PaymentResponseDTO> getAll();
    List<PaymentResponseDTO> getPaymentsByDateRange(LocalDateTime start, LocalDateTime end);
    List<PaymentResponseDTO> getPaymentsByUser(Long idUser);
    List<PaymentResponseDTO> getPaymentsByStatus(Status status);
    List<PaymentResponseDTO> getPaymentsByPriceRange(double minPrice, double maxPrice);
    PaymentResponseDTO createPayment(PaymentRequestDTO paymentDTO);
    PaymentResponseDTO changeStatus(Long idPayment, Status status);
    String deletePayment(Long idPayment);
}
