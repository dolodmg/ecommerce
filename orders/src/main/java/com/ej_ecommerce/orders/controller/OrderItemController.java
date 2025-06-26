package com.ej_ecommerce.orders.controller;

import com.ej_ecommerce.orders.dto.response.OrderItemResponseDTO;
import com.ej_ecommerce.orders.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderitems")
@Tag(name = "Order Items", description = "Order Items Endpoints")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{idItem}")
    @Operation(summary = "Retrieve an item by its ID")
    public ResponseEntity<OrderItemResponseDTO> getItemById(@PathVariable Long idItem) {
        OrderItemResponseDTO order = orderItemService.getItemById(idItem);
        return ResponseEntity.ok(order);
    }
}
