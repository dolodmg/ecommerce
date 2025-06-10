package com.ej_ecommerce.orders.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {
    private Long idItem;
    private Long idProduct;
    private int quantity;
    private Double price;
}
