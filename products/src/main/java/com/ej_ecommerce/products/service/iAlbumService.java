package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.AlbumRequestDTO;
import com.ej_ecommerce.products.dto.response.AlbumResponseDTO;
import com.ej_ecommerce.products.model.Album;

import java.util.List;

public interface iAlbumService {
    public AlbumResponseDTO getAlbum(Long idAlbum);
    public List<AlbumResponseDTO> getAll();
    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumDTO);
    public String deleteAlbum(Long idAlbum);
    public AlbumResponseDTO editAlbum(Long idAlbum, AlbumRequestDTO albumDTO);
}
