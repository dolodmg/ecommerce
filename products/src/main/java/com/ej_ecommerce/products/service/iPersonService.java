package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.PersonRequestDTO;
import com.ej_ecommerce.products.dto.response.PersonResponseDTO;

import java.util.List;

public interface iPersonService {
    PersonResponseDTO getPerson(Long idPerson);
    List<PersonResponseDTO> getAll();
    PersonResponseDTO createPerson(PersonRequestDTO personDTO);
    String deletePerson(Long idPerson);
    PersonResponseDTO editPerson(Long idPerson, PersonRequestDTO personDTO);
}
