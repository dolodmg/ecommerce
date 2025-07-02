package com.ej_ecommerce.orders.dto.response;

import com.ej_ecommerce.orders.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long idOrder;
    private Long idUser;
    private double totalPrice;
    private LocalDate orderDate;
    private boolean active;
    private Status status;
    private List<OrderItemResponseDTO> items;
}
