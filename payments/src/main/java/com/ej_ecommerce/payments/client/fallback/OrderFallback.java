package com.ej_ecommerce.payments.client.fallback;

import com.ej_ecommerce.payments.client.feign.OrderAPIClient;
import com.ej_ecommerce.payments.dto.response.OrderResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OrderFallback implements OrderAPIClient {
    @Override
    public OrderResponseDTO getOrder(Long idOrder) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
        "Error al obtener las órdenes");
    }
    @Override
    public OrderResponseDTO changeStatus(Long idOrder, String status) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "Error al cambiar el estado de la orden");
    }
}
