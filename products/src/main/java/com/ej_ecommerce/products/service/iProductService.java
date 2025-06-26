package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.response.ProductResponseDTO;

import java.util.List;

public interface iProductService {
    ProductResponseDTO getProduct(Long idProduct);
    List<ProductResponseDTO> getAll();
    ProductResponseDTO updateStock(Long idProduct, int newStock);

}
