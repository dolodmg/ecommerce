package com.ej_ecommerce.carts.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDTO {
    private Long idItem;
    private Long idProduct;
    private int quantity;
}
