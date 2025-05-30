package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.EditorialRequestDTO;
import com.ej_ecommerce.products.dto.response.EditorialResponseDTO;
import com.ej_ecommerce.products.mapper.EditorialMapper;
import com.ej_ecommerce.products.model.Editorial;
import com.ej_ecommerce.products.repository.EditorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EditorialService implements iEditorialService {
    private final EditorialRepository editorialRepository;
    private final EditorialMapper editorialMapper;

    public EditorialService(EditorialRepository editorialRepository, EditorialMapper editorialMapper) {
        this.editorialRepository = editorialRepository;
        this.editorialMapper = editorialMapper;
    }

    @Override
    public EditorialResponseDTO getEditorial(Long idEditorial) {
        Editorial editorial = editorialRepository.findById(idEditorial)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la editorial con id " + idEditorial));
        return editorialMapper.toDto(editorial);
    }

    @Override
    public List<EditorialResponseDTO> getAll() {
        List<Editorial> editoriales = editorialRepository.findAll();
        return editoriales.stream()
                .map(editorialMapper::toDto)
                .toList();
    }

    @Override
    public EditorialResponseDTO createEditorial(EditorialRequestDTO editorialDTO) {
        Editorial editorial = editorialMapper.toEntity(editorialDTO);
        editorial = editorialRepository.save(editorial);
        return editorialMapper.toDto(editorial);
    }

    @Override
    public String deleteEditorial(Long idEditorial) {
        try {
            editorialRepository.deleteById(idEditorial);
            return "La editorial se eliminó correctamente";
        } catch (Exception e) {
            return "No se pudo eliminar la editorial: " + e.getMessage();
        }
    }

    @Override
    public EditorialResponseDTO editEditorial(Long idEditorial, EditorialRequestDTO editorialDTO) {
        Editorial existing = editorialRepository.findById(idEditorial)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar la editorial con id " + idEditorial));

        existing.setName(editorialDTO.getName());

        Editorial updated = editorialRepository.save(existing);
        return editorialMapper.toDto(updated);
    }
}
