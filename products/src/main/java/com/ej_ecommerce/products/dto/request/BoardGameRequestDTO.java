package com.ej_ecommerce.products.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardGameRequestDTO {
    private String name;
    private Long idBrand;
    private Double price;
    private int stock;
    private String imageUrl;
    private String description;
}
