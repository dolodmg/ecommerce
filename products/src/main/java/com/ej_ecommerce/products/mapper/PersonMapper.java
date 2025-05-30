package com.ej_ecommerce.products.mapper;

import com.ej_ecommerce.products.dto.request.PersonRequestDTO;
import com.ej_ecommerce.products.dto.response.PersonResponseDTO;
import com.ej_ecommerce.products.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "idPerson", ignore = true)
    Person toEntity(PersonRequestDTO dto);

    PersonResponseDTO toDto(Person person);
}
