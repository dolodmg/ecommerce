package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BrandRequestDTO;
import com.ej_ecommerce.products.dto.response.BrandResponseDTO;
import com.ej_ecommerce.products.model.Brand;

import java.util.List;

public interface iBrandService {
    public BrandResponseDTO getBrand(Long idBrand);
    public List<BrandResponseDTO> getAll();
    public BrandResponseDTO createBrand(BrandRequestDTO brandDTO);
    public String deleteBrand(Long idBrand);
    public BrandResponseDTO editBrand(Long idBrand, BrandRequestDTO brandDTO);
}
