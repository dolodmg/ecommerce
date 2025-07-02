package com.ej_ecommerce.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardGameResponseDTO {
    private Long idProduct;
    private String name;
    private BrandResponseDTO brand;
    private double price;
    private int stock;
    private String imageUrl;
    private String description;
}
