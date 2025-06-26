package com.ej_ecommerce.orders.service;

import com.ej_ecommerce.orders.dto.request.OrderRequestDTO;
import com.ej_ecommerce.orders.dto.response.*;
import com.ej_ecommerce.orders.mapper.OrderMapper;
import com.ej_ecommerce.orders.model.Order;
import com.ej_ecommerce.orders.model.OrderItem;
import com.ej_ecommerce.orders.model.Status;
import com.ej_ecommerce.orders.client.feign.CartAPIClient;
import com.ej_ecommerce.orders.repository.OrderRepository;
import com.ej_ecommerce.orders.client.feign.ProductAPIClient;
import com.ej_ecommerce.orders.client.feign.UserAPIClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements iOrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductAPIClient productAPIClient;
    private final CartAPIClient cartAPIClient;
    private final UserAPIClient userAPIClient;

    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        @Qualifier("com.ej_ecommerce.orders.client.feign.ProductAPIClient") ProductAPIClient productAPIClient,
                        @Qualifier("com.ej_ecommerce.orders.client.feign.CartAPIClient") CartAPIClient cartAPIClient,
                        @Qualifier("com.ej_ecommerce.orders.client.feign.UserAPIClient") UserAPIClient userAPIClient) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productAPIClient = productAPIClient;
        this.cartAPIClient = cartAPIClient;
        this.userAPIClient = userAPIClient;
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
        try {
            UserResponseDTO user = userAPIClient.getUser(idUser);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario con ID " + idUser);
        }
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
        try {
            userAPIClient.getUser(orderDTO.getIdUser());
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario con ID " + orderDTO.getIdUser());
        }
        CartResponseDTO cartDTO = cartAPIClient.getCart(orderDTO.getIdCart());
        if (cartDTO == null || cartDTO.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El carrito está vacío o no existe");
        }
        Order order = new Order();
        order.setIdUser(cartDTO.getIdUser());
        order.setOrderDate(LocalDate.now());
        order.setStatus(Status.PENDING);
        order.setActive(true);
        double totalPrice = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemResponseDTO cartItem : cartDTO.getItems()) {
            ProductResponseDTO product = productAPIClient.getProduct(cartItem.getIdProduct());
            if (product.getStock() < cartItem.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El producto no tiene stock suficiente");
            }
            double itemPrice = product.getPrice() * cartItem.getQuantity();
            totalPrice+= itemPrice;
            OrderItem orderItem = new OrderItem();
            orderItem.setIdProduct(cartItem.getIdProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(itemPrice);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            int newStock = product.getStock() - cartItem.getQuantity();
            productAPIClient.updateStock(cartItem.getIdProduct(), newStock);
        }
        order.setItems(orderItems);
        order.setTotalPrice(totalPrice);
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
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
