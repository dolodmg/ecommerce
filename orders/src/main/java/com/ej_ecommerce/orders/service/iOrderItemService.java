package com.ej_ecommerce.orders.service;

import com.ej_ecommerce.orders.dto.response.OrderItemResponseDTO;

import java.util.List;

public interface iOrderItemService {
    public OrderItemResponseDTO getItemById(Long idItem);
}
