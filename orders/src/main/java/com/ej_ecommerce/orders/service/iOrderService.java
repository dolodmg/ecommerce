package com.ej_ecommerce.orders.service;

import com.ej_ecommerce.orders.dto.request.OrderRequestDTO;
import com.ej_ecommerce.orders.dto.response.OrderResponseDTO;
import com.ej_ecommerce.orders.model.Status;

import java.time.LocalDate;
import java.util.List;

public interface iOrderService {
    OrderResponseDTO getOrder(Long idOrder);
    List<OrderResponseDTO> getAll();
    List<OrderResponseDTO> getOrderByUserId(Long idUser);
    List<OrderResponseDTO> getOrdersByStatus(Status status);
    List<OrderResponseDTO> getOrdersByDateRange(LocalDate start, LocalDate end);
    OrderResponseDTO createOrder(OrderRequestDTO orderDTO);
    OrderResponseDTO changeStatus(Long idOrder, Status status);
    String deleteOrder(Long idOrder);
    }
