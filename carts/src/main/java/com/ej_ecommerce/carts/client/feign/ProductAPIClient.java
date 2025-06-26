package com.ej_ecommerce.carts.client.feign;

import com.ej_ecommerce.carts.client.fallback.ProductFallback;
import com.ej_ecommerce.carts.dto.response.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productapi", url = "http://localhost:8082/products", fallback = ProductFallback.class)
public interface ProductAPIClient {
    @GetMapping("/get/{idProduct}")
    ProductResponseDTO getProduct(@PathVariable("idProduct") Long idProduct);
}
