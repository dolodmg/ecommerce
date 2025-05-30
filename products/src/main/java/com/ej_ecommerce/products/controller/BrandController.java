package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.BrandRequestDTO;
import com.ej_ecommerce.products.dto.response.BrandResponseDTO;
import com.ej_ecommerce.products.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/get/{idBrand}")
    public ResponseEntity<BrandResponseDTO> getBrand(@PathVariable Long idBrand) {
        BrandResponseDTO brand = brandService.getBrand(idBrand);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BrandResponseDTO>> getAll() {
        List<BrandResponseDTO> brands = brandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @PostMapping("/post")
    public ResponseEntity<BrandResponseDTO> createBrand(@RequestBody BrandRequestDTO brandDTO) {
        BrandResponseDTO brand = brandService.createBrand(brandDTO);
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping("/delete/{idBrand}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long idBrand) {
        String message = brandService.deleteBrand(idBrand);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/edit/{idBrand}")
    public ResponseEntity<BrandResponseDTO> editBrand(@PathVariable Long idBrand, @RequestBody BrandRequestDTO brandDTO) {
        BrandResponseDTO updated = brandService.editBrand(idBrand, brandDTO);
        return ResponseEntity.ok(updated);
    }
}
