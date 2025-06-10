package com.ej_ecommerce.orders.service;

import com.ej_ecommerce.orders.dto.response.OrderItemResponseDTO;
import com.ej_ecommerce.orders.mapper.OrderItemMapper;
import com.ej_ecommerce.orders.model.OrderItem;
import com.ej_ecommerce.orders.repository.OrderItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderItemService implements iOrderItemService {
    private final OrderItemRepository itemRepository;
    private final OrderItemMapper itemMapper;

    public OrderItemService(OrderItemRepository itemRepository, OrderItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public OrderItemResponseDTO getItemById(Long idItem) {
        OrderItem item = itemRepository.findById(idItem)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un item con ID " + idItem));
        return itemMapper.toDto(item);
    }

}
