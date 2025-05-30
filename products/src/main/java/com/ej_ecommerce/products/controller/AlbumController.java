package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.AlbumRequestDTO;
import com.ej_ecommerce.products.dto.response.AlbumResponseDTO;
import com.ej_ecommerce.products.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/get/{idAlbum}")
    public ResponseEntity<AlbumResponseDTO> getAlbum(@PathVariable Long idAlbum) {
        AlbumResponseDTO album = albumService.getAlbum(idAlbum);
        return ResponseEntity.ok(album);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<AlbumResponseDTO>> getAll() {
        List<AlbumResponseDTO> albums = albumService.getAll();
        return ResponseEntity.ok(albums);
    }

    @PostMapping("/post")
    public ResponseEntity<AlbumResponseDTO> createAlbum(@RequestBody AlbumRequestDTO albumDTO) {
        AlbumResponseDTO album = albumService.createAlbum(albumDTO);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/edit/{idAlbum}")
    public ResponseEntity<AlbumResponseDTO> editAlbum(@PathVariable Long idAlbum, @RequestBody AlbumRequestDTO albumDTO) {
        AlbumResponseDTO updated = albumService.editAlbum(idAlbum, albumDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{idAlbum}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long idAlbum) {
        String message = albumService.deleteAlbum(idAlbum);
        return ResponseEntity.ok(message);
    }
}
