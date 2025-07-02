package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.AlbumRequestDTO;
import com.ej_ecommerce.products.dto.response.AlbumResponseDTO;

import java.util.List;

public interface iAlbumService {
    AlbumResponseDTO getAlbum(Long idProduct);
    List<AlbumResponseDTO> getAll();
    AlbumResponseDTO createAlbum(AlbumRequestDTO albumDTO);
    String deleteAlbum(Long idProduct);
    AlbumResponseDTO editAlbum(Long idProduct, AlbumRequestDTO albumDTO);
}
