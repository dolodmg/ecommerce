package com.ej_ecommerce.carts.client.fallback;

import com.ej_ecommerce.carts.client.feign.UserAPIClient;
import com.ej_ecommerce.carts.dto.response.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserFallback implements UserAPIClient {
    @Override
    public UserResponseDTO getUser(Long idUser) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "Servicio de usuarios no disponible");
    }
}
