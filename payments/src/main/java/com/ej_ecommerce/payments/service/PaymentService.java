package com.ej_ecommerce.payments.service;

import com.ej_ecommerce.payments.dto.request.PaymentRequestDTO;
import com.ej_ecommerce.payments.dto.response.PaymentResponseDTO;
import com.ej_ecommerce.payments.mapper.PaymentMapper;
import com.ej_ecommerce.payments.model.Payment;
import com.ej_ecommerce.payments.model.Status;
import com.ej_ecommerce.payments.client.feign.OrderAPIClient;
import com.ej_ecommerce.payments.repository.PaymentRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService implements iPaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderAPIClient orderAPIClient;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, @Qualifier("com.ej_ecommerce.payments.client.feign.OrderAPIClient") OrderAPIClient orderAPIClient) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.orderAPIClient = orderAPIClient;
    }

    @Override
    public PaymentResponseDTO getPayment(Long idPayment) {
        Payment payment = paymentRepository.findById(idPayment)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un pago con el ID " + idPayment));
        return paymentMapper.toDto(payment);
    }

    @Override
    public List<PaymentResponseDTO> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByDateRange(LocalDateTime start, LocalDateTime end) {
        List<Payment> payments = paymentRepository.findAllByDateTimeBetween(start, end);
        return payments.stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByUser(Long idUser) {
        List<Payment> payments = paymentRepository.findAllByIdUser(idUser);
        return payments.stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByStatus(Status status) {
        List<Payment> payments = paymentRepository.findAllByStatus(status);
        return payments.stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByPriceRange(Double minPrice, Double maxPrice) {
        List<Payment> payments = paymentRepository.findAllByTotalBetween(minPrice, maxPrice);
        return payments.stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentDTO) {
        Payment payment = paymentMapper.toEntity(paymentDTO);
        payment = paymentRepository.save(payment);
        return paymentMapper.toDto(payment);
    }

    @Override
    public PaymentResponseDTO changeStatus(Long idPayment, Status status) {
        Payment existing = paymentRepository.findById(idPayment)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un pago con el ID " + idPayment));
        existing.setStatus(status);
        Payment payment = paymentRepository.save(existing);
        if (status == Status.APPROVED) {
            try {
                orderAPIClient.changeStatus(existing.getIdOrder(), "PAID");
            } catch (FeignException e) {
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "No se pudo actualizar el estado de la orden");
            }
        }
        return paymentMapper.toDto(payment);
    }

    @Override
    public String deletePayment(Long idPayment) {
        Payment existing = paymentRepository.findById(idPayment)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un pago con el ID " + idPayment));
        existing.setActive(false);
        paymentRepository.save(existing);
        return "El pago fue eliminado correctamente";
    }
}
