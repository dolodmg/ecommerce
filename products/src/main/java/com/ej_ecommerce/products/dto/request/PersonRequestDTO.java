package com.ej_ecommerce.products.dto.request;

import com.ej_ecommerce.products.model.PersonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDTO {
    private String firstName;
    private String lastName;
    private PersonType personType;
}
