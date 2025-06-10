package com.ej_ecommerce.orders.mapper;

import com.ej_ecommerce.orders.dto.request.OrderRequestDTO;
import com.ej_ecommerce.orders.dto.response.OrderResponseDTO;
import com.ej_ecommerce.orders.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "idOrder", ignore = true)
    Order toEntity(OrderRequestDTO dto);

    OrderResponseDTO toDto(Order order);
}
