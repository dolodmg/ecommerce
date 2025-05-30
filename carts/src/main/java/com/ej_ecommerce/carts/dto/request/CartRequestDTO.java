package com.ej_ecommerce.carts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {
    private Long idUser;
    private List<CartItemRequestDTO> items;
}
