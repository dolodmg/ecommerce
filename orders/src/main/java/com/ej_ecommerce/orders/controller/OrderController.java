package com.ej_ecommerce.orders.controller;

import com.ej_ecommerce.orders.dto.request.OrderRequestDTO;
import com.ej_ecommerce.orders.dto.response.OrderResponseDTO;
import com.ej_ecommerce.orders.model.Status;
import com.ej_ecommerce.orders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Orders Endpoints")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{idOrder}")
    @Operation(summary = "Retrieve an order by its ID")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long idOrder) {
        OrderResponseDTO order = orderService.getOrder(idOrder);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/all")
    @Operation(summary = "Retrieve all orders")
    public ResponseEntity<List<OrderResponseDTO>> getAll() {
        List<OrderResponseDTO> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{idUser}")
    @Operation(summary = "Retrieve all the orders of a user")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByUserId(@PathVariable Long idUser) {
        List<OrderResponseDTO> orders = orderService.getOrderByUserId(idUser);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status")
    @Operation(summary = "Retrieve orders by status")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByStatus(@RequestParam Status status) {
        List<OrderResponseDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    @Operation(summary = "Retrieve all the orders in a given date range")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<OrderResponseDTO> orders = orderService.getOrdersByDateRange(start, end);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new order")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderDTO) {
        OrderResponseDTO order = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{idOrder}/status")
    @Operation(summary = "Change the status of an order")
    public ResponseEntity<OrderResponseDTO> changeStatus(@PathVariable Long idOrder, @RequestParam Status status) {
        OrderResponseDTO order = orderService.changeStatus(idOrder, status);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{idOrder}")
    @Operation(summary = "Logically delete an order")
    public ResponseEntity<String> deleteOrder(@PathVariable Long idOrder) {
        String message = orderService.deleteOrder(idOrder);
        return ResponseEntity.ok(message);
    }
}
