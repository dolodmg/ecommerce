package com.ej_ecommerce.orders.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {
    private Long idCart;
    private Long idUser;
    private List<CartItemResponseDTO> items;
}
