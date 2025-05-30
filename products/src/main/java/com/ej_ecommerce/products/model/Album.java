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
public class Album extends Product {
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long idAlbum;
    private String title;
    private Long code;
    private Date publicationDate;
    @Enumerated(EnumType.STRING)
    private AlbumFormat albumFormat;
    @ManyToOne
    private Person person;
}
