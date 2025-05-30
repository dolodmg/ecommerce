package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.PersonRequestDTO;
import com.ej_ecommerce.products.dto.response.PersonResponseDTO;
import com.ej_ecommerce.products.mapper.PersonMapper;
import com.ej_ecommerce.products.model.Person;
import com.ej_ecommerce.products.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService implements iPersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }
    @Override
    public PersonResponseDTO getPerson(Long idPerson) {
        Person person = personRepository.findById(idPerson)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la persona con id " + idPerson));
        return personMapper.toDto(person);
    }

    @Override
    public List<PersonResponseDTO> getAll() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(personMapper::toDto)
                .toList();
    }

    @Override
    public PersonResponseDTO createPerson(PersonRequestDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    @Override
    public String deletePerson(Long idPerson) {
        try {
            personRepository.deleteById(idPerson);
            return "La persona fue eliminada correctamente";
        } catch (Exception e) {
            return "No se pudo eliminar la persona: " + e.getMessage();
        }
    }

    @Override
    public PersonResponseDTO editPerson(Long idPerson, PersonRequestDTO personDTO) {
        Person existing = personRepository.findById(idPerson)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la persona con id " + idPerson));

        existing.setFirstName(personDTO.getFirstName());
        existing.setLastName(personDTO.getLastName());

        Person updated = personRepository.save(existing);
        return personMapper.toDto(updated);
    }
}