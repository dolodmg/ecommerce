package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BrandRequestDTO;
import com.ej_ecommerce.products.dto.response.BrandResponseDTO;
import com.ej_ecommerce.products.mapper.BrandMapper;
import com.ej_ecommerce.products.model.Brand;
import com.ej_ecommerce.products.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BrandService implements iBrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    public BrandResponseDTO getBrand(Long idBrand) {
        Brand brand = brandRepository.findById(idBrand)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la marca con id " + idBrand));
        return brandMapper.toDto(brand);
    }

    public List<BrandResponseDTO> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(brandMapper::toDto)
                .toList();
    }

    public BrandResponseDTO createBrand(BrandRequestDTO brandDTO) {
        Brand brand = brandMapper.toEntity(brandDTO);
        brand = brandRepository.save(brand);
        return brandMapper.toDto(brand);
    }

    public String deleteBrand(Long idBrand) {
        try {
            brandRepository.deleteById(idBrand);
            return "La marca fue eliminada correctamente";
        } catch (Exception e) {
            return "No se pudo eliminar la marca" + e.getMessage();
        }
    }

    public BrandResponseDTO editBrand(Long idBrand, BrandRequestDTO brandDTO) {
        Brand existing = brandRepository.findById(idBrand)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la marca con ID " + idBrand));

        existing.setName(brandDTO.getName());

        Brand updated = brandRepository.save(existing);
        return brandMapper.toDto(updated);
    }
}
