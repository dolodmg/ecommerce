package com.ej_ecommerce.products.dto.response;

import com.ej_ecommerce.products.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    private Long idBook;
    private String title;
    private String ISBN;
    private Date publicationDate;
    private Genre genre;
    private String editorialName;
    private PersonResponseDTO person;
    private Double price;
    private int stock;
    private String imageUrl;
    private String description;
}
