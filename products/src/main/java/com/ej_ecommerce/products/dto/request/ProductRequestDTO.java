package com.ej_ecommerce.products.dto.request;

import com.ej_ecommerce.products.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private double price;
    private Category category;
    private int stock;
    private String imageUrl;
    private String description;
}
