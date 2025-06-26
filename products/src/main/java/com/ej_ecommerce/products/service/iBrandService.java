package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BrandRequestDTO;
import com.ej_ecommerce.products.dto.response.BrandResponseDTO;

import java.util.List;

public interface iBrandService {
    BrandResponseDTO getBrand(Long idBrand);
    List<BrandResponseDTO> getAll();
    BrandResponseDTO createBrand(BrandRequestDTO brandDTO);
    String deleteBrand(Long idBrand);
    BrandResponseDTO editBrand(Long idBrand, BrandRequestDTO brandDTO);
}
