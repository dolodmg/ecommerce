package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.response.ProductResponseDTO;
import com.ej_ecommerce.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Products Endpoints")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{idProduct}")
    @Operation(summary = "Retrieve a product by its ID")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long idProduct) {
        ProductResponseDTO product = productService.getProduct(idProduct);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/get/all")
    @Operation(summary = "Retrieve all the products")
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        List<ProductResponseDTO> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{idProduct}/stock")
    @Operation(summary = "Update the stock of a product")
    public ResponseEntity<ProductResponseDTO> updateStock(@PathVariable Long idProduct, @RequestParam int newStock) {
        ProductResponseDTO product = productService.updateStock(idProduct, newStock);
        return ResponseEntity.ok(product);
    }
}
