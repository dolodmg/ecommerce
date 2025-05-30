package com.ej_ecommerce.products.dto.request;
import com.ej_ecommerce.products.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    private String title;
    private String ISBN;
    private Date publicationDate;
    private Genre genre;
    private Long idEditorial;
    private Long idPerson;
    private Double price;
    private int stock;
    private String imageUrl;
    private String description;
}
