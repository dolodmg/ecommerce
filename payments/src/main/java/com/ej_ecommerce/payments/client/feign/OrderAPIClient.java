package com.ej_ecommerce.payments.client.feign;

import com.ej_ecommerce.payments.client.fallback.OrderFallback;
import com.ej_ecommerce.payments.dto.response.OrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "orderapi", url = "http://localhost:8084/orders", fallback = OrderFallback.class)
public interface OrderAPIClient {
    @PutMapping("/{idOrder}/status")
    OrderResponseDTO changeStatus(@PathVariable("idOrder") Long idOrder, @RequestParam("status") String status);
}
