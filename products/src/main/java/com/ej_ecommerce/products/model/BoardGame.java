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
public class BoardGame extends Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBoardGame;
    private String name;
    @ManyToOne
    private Brand brand;
}
