package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.EditorialRequestDTO;
import com.ej_ecommerce.products.dto.response.EditorialResponseDTO;

import java.util.List;

public interface iEditorialService {
    EditorialResponseDTO getEditorial(Long idEditorial);
    List<EditorialResponseDTO> getAll();
    EditorialResponseDTO createEditorial(EditorialRequestDTO editorialDTO);
    String deleteEditorial(Long idEditorial);
    EditorialResponseDTO editEditorial(Long idEditorial, EditorialRequestDTO editorialDTO);
}
