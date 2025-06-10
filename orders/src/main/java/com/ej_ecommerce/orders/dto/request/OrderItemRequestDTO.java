package com.ej_ecommerce.orders.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDTO {
    private Long idProduct;
    private int quantity;
    private Double price;
}
