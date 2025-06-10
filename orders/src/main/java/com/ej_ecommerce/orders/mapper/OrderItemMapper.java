package com.ej_ecommerce.orders.mapper;

import com.ej_ecommerce.orders.dto.request.OrderItemRequestDTO;
import com.ej_ecommerce.orders.dto.response.OrderItemResponseDTO;
import com.ej_ecommerce.orders.dto.response.OrderResponseDTO;
import com.ej_ecommerce.orders.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "idItem", ignore = true)
    OrderItem toEntity(OrderItemRequestDTO dto);

    OrderItemResponseDTO toDto(OrderItem item);
}
