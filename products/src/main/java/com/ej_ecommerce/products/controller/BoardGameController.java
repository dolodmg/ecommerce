package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.BoardGameRequestDTO;
import com.ej_ecommerce.products.dto.response.BoardGameResponseDTO;
import com.ej_ecommerce.products.service.BoardGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boardgames")
@Tag(name = "Boardgames", description = "Boardgames Endpoints")
public class BoardGameController {
    private final BoardGameService boardGameService;

    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping("/get/{idBoardGame}")
    @Operation(summary = "Retrieve a boardgame by its ID")
    public ResponseEntity<BoardGameResponseDTO> getBoardGame(@PathVariable Long idBoardGame) {
        BoardGameResponseDTO boardgame = boardGameService.getBoardGame(idBoardGame);
        return ResponseEntity.ok(boardgame);
    }

    @GetMapping("/get/all")
    @Operation(summary = "Retrieve all the boardgames")
    public ResponseEntity<List<BoardGameResponseDTO>> getAll() {
        List<BoardGameResponseDTO> boardgames = boardGameService.getAll();
        return ResponseEntity.ok(boardgames);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new boardgame")
    public ResponseEntity<BoardGameResponseDTO> createBoardGame(@RequestBody BoardGameRequestDTO boardgameDTO) {
        BoardGameResponseDTO boardgame = boardGameService.createBoardGame(boardgameDTO);
        return ResponseEntity.ok(boardgame);
    }

    @DeleteMapping("/delete/{idBoardGame}")
    @Operation(summary = "Delete a boardgame")
    public ResponseEntity<String> deleteBoardGame(@PathVariable Long idBoardGame) {
        String message = boardGameService.deleteBoardGame(idBoardGame);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/edit/{idBoardGame}")
    @Operation(summary = "Edit a boardgame")
    public ResponseEntity<BoardGameResponseDTO> editBoardGame(@PathVariable Long idBoardGame, @RequestBody BoardGameRequestDTO boardgameDTO) {
        BoardGameResponseDTO updated = boardGameService.editBoardGame(idBoardGame, boardgameDTO);
        return ResponseEntity.ok(updated);
    }

}
