package com.ej_ecommerce.orders.service;

import com.ej_ecommerce.orders.dto.request.OrderRequestDTO;
import com.ej_ecommerce.orders.dto.response.OrderResponseDTO;
import com.ej_ecommerce.orders.mapper.OrderMapper;
import com.ej_ecommerce.orders.model.Order;
import com.ej_ecommerce.orders.model.Status;
import com.ej_ecommerce.orders.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements iOrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDTO getOrder(Long idOrder) {
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una orden con ID " + idOrder));
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderResponseDTO> getOrderByUserId(Long idUser) {
        List<Order> orders = orderRepository.findAllByIdUser(idUser);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderResponseDTO> getOrdersByStatus(Status status) {
        List<Order> orders = orderRepository.findOrderByStatus(status);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderResponseDTO> getOrdersByDateRange(LocalDate start, LocalDate end) {
        List<Order> orders = orderRepository.findAllByOrderDateBetween(start, end);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponseDTO changeStatus(Long idOrder, Status status) {
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el orden con ID " + idOrder));
        order.setStatus(status);
        Order updated = orderRepository.save(order);
        return orderMapper.toDto(updated);
    }

    @Override
    public String deleteOrder(Long idOrder) {
        Order existing = orderRepository.findById(idOrder)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una orden con ID " + idOrder));
        existing.setActive(false);
        orderRepository.save(existing);
        return "La orden de compra se eliminó correctamente";
    }
}
