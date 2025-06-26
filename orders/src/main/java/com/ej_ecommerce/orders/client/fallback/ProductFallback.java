package com.ej_ecommerce.orders.client.fallback;

import com.ej_ecommerce.orders.client.feign.ProductAPIClient;
import com.ej_ecommerce.orders.dto.response.ProductResponseDTO;
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

    @Override
    public ProductResponseDTO updateStock(Long idProduct, int newStock) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "Error al actualizar el stock");
    }
}
