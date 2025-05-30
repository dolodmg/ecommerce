package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.response.ProductResponseDTO;
import com.ej_ecommerce.products.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{idProduct}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long idProduct) {
        ProductResponseDTO product = productService.getProduct(idProduct);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        List<ProductResponseDTO> products = productService.getAll();
        return ResponseEntity.ok(products);
    }
}
