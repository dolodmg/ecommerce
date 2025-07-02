package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.AlbumRequestDTO;
import com.ej_ecommerce.products.dto.response.AlbumResponseDTO;
import com.ej_ecommerce.products.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@Tag(name = "Albums", description = "Albums Endpoints")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/{idProduct}")
    @Operation(summary = "Retrieve an album by its ID")
    public ResponseEntity<AlbumResponseDTO> getAlbum(@PathVariable Long idProduct) {
        AlbumResponseDTO album = albumService.getAlbum(idProduct);
        return ResponseEntity.ok(album);
    }

    @GetMapping("/all")
    @Operation(summary = "Retrieve all the albums")
    public ResponseEntity<List<AlbumResponseDTO>> getAll() {
        List<AlbumResponseDTO> albums = albumService.getAll();
        return ResponseEntity.ok(albums);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new album")
    public ResponseEntity<AlbumResponseDTO> createAlbum(@RequestBody AlbumRequestDTO albumDTO) {
        AlbumResponseDTO album = albumService.createAlbum(albumDTO);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/edit/{idProduct}")
    @Operation(summary = "Edit an album")
    public ResponseEntity<AlbumResponseDTO> editAlbum(@PathVariable Long idProduct, @RequestBody AlbumRequestDTO albumDTO) {
        AlbumResponseDTO updated = albumService.editAlbum(idProduct, albumDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{idProduct}")
    @Operation(summary = "Delete an album")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long idProduct) {
        String message = albumService.deleteAlbum(idProduct);
        return ResponseEntity.ok(message);
    }
}
