package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.response.ProductResponseDTO;

import java.util.List;

public interface iProductService {
    public ProductResponseDTO getProduct(Long idProduct);
    public List<ProductResponseDTO> getAll();
}
