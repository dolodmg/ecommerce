package com.ej_ecommerce.products.dto.response;

import com.ej_ecommerce.products.model.PersonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {
    private Long idPerson;
    private String firstName;
    private String lastName;
}
