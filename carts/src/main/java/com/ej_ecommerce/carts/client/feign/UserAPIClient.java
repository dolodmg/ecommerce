package com.ej_ecommerce.carts.client.feign;

import com.ej_ecommerce.carts.client.fallback.UserFallback;
import com.ej_ecommerce.carts.dto.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userapi", url = "http://localhost:8083/users", fallback = UserFallback.class)
public interface UserAPIClient {
    @GetMapping("/get/{idUser}")
    UserResponseDTO getUser(@PathVariable("idUser") Long idUser);
}
