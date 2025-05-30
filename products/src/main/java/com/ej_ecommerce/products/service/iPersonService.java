package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.PersonRequestDTO;
import com.ej_ecommerce.products.dto.response.PersonResponseDTO;

import java.util.List;

public interface iPersonService {
    public PersonResponseDTO getPerson(Long idPerson);
    public List<PersonResponseDTO> getAll();
    public PersonResponseDTO createPerson(PersonRequestDTO personDTO);
    public String deletePerson(Long idPerson);
    public PersonResponseDTO editPerson(Long idPerson, PersonRequestDTO personDTO);
}
