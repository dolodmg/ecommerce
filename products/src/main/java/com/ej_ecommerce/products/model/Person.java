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
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPerson;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private PersonType personType;
}
