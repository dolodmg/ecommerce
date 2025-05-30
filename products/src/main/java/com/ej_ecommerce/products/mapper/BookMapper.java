package com.ej_ecommerce.products.mapper;

import com.ej_ecommerce.products.dto.request.BookRequestDTO;
import com.ej_ecommerce.products.dto.response.BookResponseDTO;
import com.ej_ecommerce.products.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mappings({
            @Mapping(source = "idEditorial", target = "editorial.idEditorial"),
            @Mapping(source = "idPerson", target = "person.idPerson"),
            @Mapping(target = "idBook", ignore = true)
    })

    Book toEntity(BookRequestDTO dto);

    @Mappings({
            @Mapping(source = "editorial.name", target = "editorialName"),
            @Mapping(source = "person", target = "person")
    })

    BookResponseDTO toDto(Book book);
}
