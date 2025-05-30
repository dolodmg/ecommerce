package com.ej_ecommerce.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardGameResponseDTO {
    private String name;
    private BrandResponseDTO brand;
    private Double price;
    private int stock;
    private String imageUrl;
    private String description;
}
