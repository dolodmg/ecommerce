package com.ej_ecommerce.products.dto.response;

import com.ej_ecommerce.products.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Long idProduct;
    private Double price;
    private Category category;
    private int stock;
    private String imageUrl;
    private String description;
}
