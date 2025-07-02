package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.BrandRequestDTO;
import com.ej_ecommerce.products.dto.response.BrandResponseDTO;
import com.ej_ecommerce.products.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brands", description = "Brands Endpoints")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/get/{idBrand}")
    @Operation(summary = "Retrieve a brand by its ID")
    public ResponseEntity<BrandResponseDTO> getBrand(@PathVariable Long idBrand) {
        BrandResponseDTO brand = brandService.getBrand(idBrand);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("/get/all")
    @Operation(summary = "Retrieve all the items")
    public ResponseEntity<List<BrandResponseDTO>> getAll() {
        List<BrandResponseDTO> brands = brandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new brand")
    public ResponseEntity<BrandResponseDTO> createBrand(@RequestBody BrandRequestDTO brandDTO) {
        BrandResponseDTO brand = brandService.createBrand(brandDTO);
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping("/delete/{idBrand}")
    @Operation(summary = "Delete a brand")
    public ResponseEntity<String> deleteBrand(@PathVariable Long idBrand) {
        String message = brandService.deleteBrand(idBrand);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/edit/{idBrand}")
    @Operation(summary = "Edit a brand")
    public ResponseEntity<BrandResponseDTO> editBrand(@PathVariable Long idBrand, @RequestBody BrandRequestDTO brandDTO) {
        BrandResponseDTO updated = brandService.editBrand(idBrand, brandDTO);
        return ResponseEntity.ok(updated);
    }
}
