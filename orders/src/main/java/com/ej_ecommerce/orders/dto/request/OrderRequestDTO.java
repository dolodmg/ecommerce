package com.ej_ecommerce.orders.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Long idUser;
    private LocalDate orderDate;
    private List<OrderItemRequestDTO> items;
}
