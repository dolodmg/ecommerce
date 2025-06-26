package com.ej_ecommerce.carts.client.fallback;

import com.ej_ecommerce.carts.client.feign.ProductAPIClient;
import com.ej_ecommerce.carts.dto.response.ProductResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ProductFallback implements ProductAPIClient {
    @Override
    public ProductResponseDTO getProduct(Long idProduct) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "Servicio de productos no disponible");
    }
}
