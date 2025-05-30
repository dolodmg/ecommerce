package com.ej_ecommerce.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduct;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Category category;
    private int stock;
    private String imageUrl;
    private String description;
}
