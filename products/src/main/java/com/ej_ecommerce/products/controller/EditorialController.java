package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.EditorialRequestDTO;
import com.ej_ecommerce.products.dto.response.EditorialResponseDTO;
import com.ej_ecommerce.products.service.EditorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editorials")
public class EditorialController {
    private final EditorialService editorialService;

    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping("/get/{idEditorial}")
    public ResponseEntity<EditorialResponseDTO> getEditorial(@PathVariable Long idEditorial) {
        EditorialResponseDTO editorial = editorialService.getEditorial(idEditorial);
        return ResponseEntity.ok(editorial);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<EditorialResponseDTO>> getAll() {
        List<EditorialResponseDTO> editorials = editorialService.getAll();
        return ResponseEntity.ok(editorials);
    }

    @PostMapping("/post")
    public ResponseEntity<EditorialResponseDTO> createEditorial(@RequestBody EditorialRequestDTO editorialDTO) {
        EditorialResponseDTO editorial = editorialService.createEditorial(editorialDTO);
        return ResponseEntity.ok(editorial);
    }

    @DeleteMapping("/delete/{idEditorial}")
    public ResponseEntity<String> deleteEditorial(@PathVariable Long idEditorial) {
        String message = editorialService.deleteEditorial(idEditorial);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/edit/{idEditorial}")
    public ResponseEntity<EditorialResponseDTO> editEditorial(@PathVariable Long idEditorial, @RequestBody EditorialRequestDTO editorialDTO) {
        EditorialResponseDTO updated = editorialService.editEditorial(idEditorial, editorialDTO);
        return ResponseEntity.ok(updated);
    }
}
