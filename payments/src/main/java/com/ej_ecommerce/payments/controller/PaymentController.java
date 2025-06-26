package com.ej_ecommerce.payments.controller;

import com.ej_ecommerce.payments.dto.request.PaymentRequestDTO;
import com.ej_ecommerce.payments.dto.response.PaymentResponseDTO;
import com.ej_ecommerce.payments.model.Status;
import com.ej_ecommerce.payments.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payments", description = "Payments Endpoints")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{idPayment}")
    @Operation(summary = "Retrieve a payment by its ID")
    public ResponseEntity<PaymentResponseDTO> getPayment(@PathVariable Long idPayment) {
        PaymentResponseDTO payment = paymentService.getPayment(idPayment);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/all")
    @Operation(summary = "Retrieve a list of all the payments")
    public ResponseEntity<List<PaymentResponseDTO>> getAll() {
        List<PaymentResponseDTO> payments = paymentService.getAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping
    @Operation(summary = "Retrieve all the payments in a given date range")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate
            ) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByDateRange(startDate, endDate);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{idUser}")
    @Operation(summary = "Retrieve all the payments of a user")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByUser(@PathVariable Long idUser) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByUser(idUser);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/status")
    @Operation(summary = "Retrieve payments by status")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByStatus(@RequestParam Status status) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByStatus(status);
        return ResponseEntity.ok(payments);
    }

    @GetMapping
    @Operation(summary = "Retrieve all the payments in a given price range")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice
    ) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new payment")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO paymentDTO) {
        PaymentResponseDTO payment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/{idPayment}")
    @Operation(summary = "Change the status of a payment")
    public ResponseEntity<PaymentResponseDTO> changeStatus(@PathVariable Long idPayment, @RequestParam Status status) {
        PaymentResponseDTO payment = paymentService.changeStatus(idPayment, status);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{idPayment}")
    @Operation(summary = "Logically delete a payment")
    public ResponseEntity<String> deletePayment(@PathVariable Long idPayment) {
        String message = paymentService.deletePayment(idPayment);
        return ResponseEntity.ok(message);
    }
}
