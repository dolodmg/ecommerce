package com.ej_ecommerce.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBook;
    private String title;
    private String ISBN;
    private Date publicationDate;
    private Genre genre;
    @ManyToOne
    private Editorial editorial;
    @ManyToOne
    private Person person;
}
