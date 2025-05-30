package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.response.ProductResponseDTO;
import com.ej_ecommerce.products.mapper.ProductMapper;
import com.ej_ecommerce.products.model.Product;
import com.ej_ecommerce.products.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService implements iProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDTO getProduct(Long idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el producto con id " + idProduct));
        return productMapper.toDto(product);

    }

    @Override
    public List<ProductResponseDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }
}
