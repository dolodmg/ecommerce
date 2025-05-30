package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.BoardGameRequestDTO;
import com.ej_ecommerce.products.dto.response.BoardGameResponseDTO;
import com.ej_ecommerce.products.service.BoardGameService;
import jakarta.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameController {
    private final BoardGameService boardGameService;

    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping("/get/{idBoardGame}")
    public ResponseEntity<BoardGameResponseDTO> getBoardGame(@PathVariable Long idBoardGame) {
        BoardGameResponseDTO boardgame = boardGameService.getBoardGame(idBoardGame);
        return ResponseEntity.ok(boardgame);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BoardGameResponseDTO>> getAll() {
        List<BoardGameResponseDTO> boardgames = boardGameService.getAll();
        return ResponseEntity.ok(boardgames);
    }

    @PostMapping("/post")
    public ResponseEntity<BoardGameResponseDTO> createBoardGame(@RequestBody BoardGameRequestDTO boardgameDTO) {
        BoardGameResponseDTO boardgame = boardGameService.createBoardGame(boardgameDTO);
        return ResponseEntity.ok(boardgame);
    }

    @DeleteMapping("/delete/{idBoardGame}")
    public ResponseEntity<String> deleteBoardGame(@PathVariable Long idBoardGame) {
        String message = boardGameService.deleteBoardGame(idBoardGame);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/edit/{idBoardGame}")
    public ResponseEntity<BoardGameResponseDTO> editBoardGame(@PathVariable Long idBoardGame, @RequestBody BoardGameRequestDTO boardgameDTO) {
        BoardGameResponseDTO updated = boardGameService.editBoardGame(idBoardGame, boardgameDTO);
        return ResponseEntity.ok(updated);
    }

}
