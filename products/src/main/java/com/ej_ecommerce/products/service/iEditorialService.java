package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.EditorialRequestDTO;
import com.ej_ecommerce.products.dto.response.EditorialResponseDTO;
import com.ej_ecommerce.products.model.Editorial;

import java.util.List;

public interface iEditorialService {
    public EditorialResponseDTO getEditorial(Long idEditorial);
    public List<EditorialResponseDTO> getAll();
    public EditorialResponseDTO createEditorial(EditorialRequestDTO editorialDTO);
    public String deleteEditorial(Long idEditorial);
    public EditorialResponseDTO editEditorial(Long idEditorial, EditorialRequestDTO editorialDTO);
}
