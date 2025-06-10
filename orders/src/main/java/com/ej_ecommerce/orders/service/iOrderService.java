package com.ej_ecommerce.orders.service;

import com.ej_ecommerce.orders.dto.request.OrderRequestDTO;
import com.ej_ecommerce.orders.dto.response.OrderResponseDTO;
import com.ej_ecommerce.orders.model.Status;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface iOrderService {
    public OrderResponseDTO getOrder(Long idOrder);
    public List<OrderResponseDTO> getAll();
    public List<OrderResponseDTO> getOrderByUserId(Long idUser);

    public List<OrderResponseDTO> getOrdersByStatus(Status status);
    public List<OrderResponseDTO> getOrdersByDateRange(LocalDate start, LocalDate end);
    public OrderResponseDTO createOrder(OrderRequestDTO orderDTO);
    public OrderResponseDTO changeStatus(Long idOrder, Status status);
    public String deleteOrder(Long idOrder);
}
