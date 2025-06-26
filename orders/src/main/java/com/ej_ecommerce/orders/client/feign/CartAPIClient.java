package com.ej_ecommerce.orders.client.feign;

import com.ej_ecommerce.orders.client.fallback.CartFallback;
import com.ej_ecommerce.orders.dto.response.CartResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartapi", url = "http://localhost:8081/carts", fallback = CartFallback.class)
public interface CartAPIClient {
    @GetMapping("/get/{idCart}")
    CartResponseDTO getCart(@PathVariable("idCart") Long idClient);

}
