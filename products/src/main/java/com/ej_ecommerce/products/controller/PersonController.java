package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.PersonRequestDTO;
import com.ej_ecommerce.products.dto.response.PersonResponseDTO;
import com.ej_ecommerce.products.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@Tag(name = "People", description = "People Endpoints")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get/all")
    @Operation(summary = "Retrieve all people")
    public ResponseEntity<List<PersonResponseDTO>> getAll() {
        List<PersonResponseDTO> all = personService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/get/{idPerson}")
    @Operation(summary = "Retrieve a person by its ID")
    public ResponseEntity<PersonResponseDTO> getPerson(@PathVariable Long idPerson) {
        PersonResponseDTO person = personService.getPerson(idPerson);
        return ResponseEntity.ok(person);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new person")
    public ResponseEntity<PersonResponseDTO> createPerson(@RequestBody PersonRequestDTO personDTO) {
        PersonResponseDTO created = personService.createPerson(personDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/edit/{idPerson}")
    @Operation(summary = "Edit a person")
    public ResponseEntity<PersonResponseDTO> editPerson(@PathVariable Long idPerson, @RequestBody PersonRequestDTO personDTO) {
        PersonResponseDTO updated = personService.editPerson(idPerson, personDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{idPerson}")
    @Operation(summary = "Delete a person")
    public ResponseEntity<String> deletePerson(@PathVariable Long idPerson) {
        String message = personService.deletePerson(idPerson);
        return ResponseEntity.ok(message);
    }
}
