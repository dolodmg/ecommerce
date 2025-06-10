package com.ej_ecommerce.payments.service;

import com.ej_ecommerce.payments.dto.request.PaymentRequestDTO;
import com.ej_ecommerce.payments.dto.response.PaymentResponseDTO;
import com.ej_ecommerce.payments.model.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface iPaymentService {
    public PaymentResponseDTO getPayment(Long idPayment);
    public List<PaymentResponseDTO> getAll();
    public List<PaymentResponseDTO> getPaymentsByDateRange(LocalDateTime start, LocalDateTime end);
    public List<PaymentResponseDTO> getPaymentsByUser(Long idUser);
    public List<PaymentResponseDTO> getPaymentsByStatus(Status status);
    public List<PaymentResponseDTO> getPaymentsByPriceRange(Double minPrice, Double maxPrice);
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentDTO);
    public PaymentResponseDTO changeStatus(Long idPayment, Status status);
    public String deletePayment(Long idPayment);
}
