package com.ej_ecommerce.products.mapper;

import com.ej_ecommerce.products.dto.request.AlbumRequestDTO;
import com.ej_ecommerce.products.dto.response.AlbumResponseDTO;
import com.ej_ecommerce.products.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    @Mappings({
            @Mapping(source = "idPerson", target = "person.idPerson"),
            @Mapping(target = "idAlbum", ignore = true)
    })

    Album toEntity(AlbumRequestDTO dto);

    @Mapping(source = "person", target = "person")
    AlbumResponseDTO toDto(Album album);
}
