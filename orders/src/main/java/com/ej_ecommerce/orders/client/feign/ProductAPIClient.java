package com.ej_ecommerce.orders.client.feign;

import com.ej_ecommerce.orders.client.fallback.ProductFallback;
import com.ej_ecommerce.orders.dto.response.ProductResponseDTO;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "productapi", url = "http://localhost:8082/products", fallback = ProductFallback.class)
public interface ProductAPIClient {
    @GetMapping("/get/{idProduct}")
    @Retry(name="products")
    ProductResponseDTO getProduct(@PathVariable("idProduct") Long idProduct);

    @PutMapping("/{idProduct}/stock")
    @Retry(name = "products")
    ProductResponseDTO updateStock(@PathVariable("idProduct") Long idProduct, @RequestParam("newStock") int newStock);

}
