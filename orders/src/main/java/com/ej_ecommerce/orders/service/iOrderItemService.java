package com.ej_ecommerce.orders.service;

import com.ej_ecommerce.orders.dto.response.OrderItemResponseDTO;

public interface iOrderItemService {
    OrderItemResponseDTO getItemById(Long idItem);
}
