package com.ej_ecommerce.orders.client.fallback;

import com.ej_ecommerce.orders.client.feign.CartAPIClient;
import com.ej_ecommerce.orders.dto.response.CartResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CartFallback implements CartAPIClient {
    @Override
    public CartResponseDTO getCart(Long idClient) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "Servicio de carritos no disponible");
    }
}
