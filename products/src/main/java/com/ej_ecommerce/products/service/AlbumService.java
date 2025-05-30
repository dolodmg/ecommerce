package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.AlbumRequestDTO;
import com.ej_ecommerce.products.dto.response.AlbumResponseDTO;
import com.ej_ecommerce.products.mapper.AlbumMapper;
import com.ej_ecommerce.products.model.Album;
import com.ej_ecommerce.products.model.Person;
import com.ej_ecommerce.products.repository.AlbumRepository;
import com.ej_ecommerce.products.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlbumService implements iAlbumService {
    private final AlbumRepository albumRepository;
    private final PersonRepository personRepository;
    private final AlbumMapper albumMapper;

    public AlbumService(AlbumRepository albumRepository, PersonRepository personRepository, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.personRepository = personRepository;
        this.albumMapper = albumMapper;
    }

    @Override
    public AlbumResponseDTO getAlbum(Long idAlbum) {
        Album album = albumRepository.findById(idAlbum)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el álbum con id " + idAlbum));
        return albumMapper.toDto(album);
    }

    @Override
    public List<AlbumResponseDTO> getAll() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream()
                .map(albumMapper::toDto)
                .toList();
    }

    @Override
    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumDTO) {
        Album album = albumMapper.toEntity(albumDTO);
        album = albumRepository.save(album);
        return albumMapper.toDto(album);
    }

    @Override
    public String deleteAlbum(Long idAlbum) {
        try {
            albumRepository.deleteById(idAlbum);
            return "El álbum fue eliminado correctamente";
        } catch (Exception e) {
            return "No se pudo eliminar el álbum" + e.getMessage();
        }
    }

    @Override
    public AlbumResponseDTO editAlbum(Long idAlbum, AlbumRequestDTO albumDTO) {
        Album existing = albumRepository.findById(idAlbum)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el álbum con ID " + idAlbum));

        existing.setTitle(albumDTO.getTitle());
        existing.setCode(albumDTO.getCode());
        existing.setPublicationDate(albumDTO.getPublicationDate());
        existing.setAlbumFormat(albumDTO.getAlbumFormat());
        existing.setPrice(albumDTO.getPrice());
        existing.setStock(albumDTO.getStock());
        existing.setImageUrl(albumDTO.getImageUrl());
        existing.setDescription(albumDTO.getDescription());

        Person person = personRepository.findById(albumDTO.getIdPerson())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la persona con ID " + albumDTO.getIdPerson()));
        existing.setPerson(person);

        Album updated = albumRepository.save(existing);
        return albumMapper.toDto(updated);
    }
}
