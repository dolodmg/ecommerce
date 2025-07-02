package com.ej_ecommerce.payments.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long idOrder;
    private Long idUser;
    private String status;
    private double totalPrice;
}
