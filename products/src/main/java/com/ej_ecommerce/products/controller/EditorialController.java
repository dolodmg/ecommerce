package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.EditorialRequestDTO;
import com.ej_ecommerce.products.dto.response.EditorialResponseDTO;
import com.ej_ecommerce.products.service.EditorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editorials")
@Tag(name = "Editorials", description = "Editorials Endpoints")
public class EditorialController {
    private final EditorialService editorialService;

    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping("/get/{idEditorial}")
    @Operation(summary = "Retrieve an editorial by its ID")
    public ResponseEntity<EditorialResponseDTO> getEditorial(@PathVariable Long idEditorial) {
        EditorialResponseDTO editorial = editorialService.getEditorial(idEditorial);
        return ResponseEntity.ok(editorial);
    }

    @GetMapping("/get/all")
    @Operation(summary = "Retrieve all the editorials")
    public ResponseEntity<List<EditorialResponseDTO>> getAll() {
        List<EditorialResponseDTO> editorials = editorialService.getAll();
        return ResponseEntity.ok(editorials);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new editorial")
    public ResponseEntity<EditorialResponseDTO> createEditorial(@RequestBody EditorialRequestDTO editorialDTO) {
        EditorialResponseDTO editorial = editorialService.createEditorial(editorialDTO);
        return ResponseEntity.ok(editorial);
    }

    @DeleteMapping("/delete/{idEditorial}")
    @Operation(summary = "Delete an editorial")
    public ResponseEntity<String> deleteEditorial(@PathVariable Long idEditorial) {
        String message = editorialService.deleteEditorial(idEditorial);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/edit/{idEditorial}")
    @Operation(summary = "Edit an editorial")
    public ResponseEntity<EditorialResponseDTO> editEditorial(@PathVariable Long idEditorial, @RequestBody EditorialRequestDTO editorialDTO) {
        EditorialResponseDTO updated = editorialService.editEditorial(idEditorial, editorialDTO);
        return ResponseEntity.ok(updated);
    }
}
