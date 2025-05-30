package com.ej_ecommerce.products.dto.response;

import com.ej_ecommerce.products.model.AlbumFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponseDTO {
    private Long idAlbum;
    private String title;
    private Long code;
    private Date publicationDate;
    private AlbumFormat albumFormat;
    private PersonResponseDTO person;
    private Double price;
    private int stock;
    private String imageUrl;
    private String description;
}
