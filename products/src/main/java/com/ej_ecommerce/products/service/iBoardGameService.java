package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BoardGameRequestDTO;
import com.ej_ecommerce.products.dto.response.BoardGameResponseDTO;
import com.ej_ecommerce.products.model.BoardGame;

import java.util.List;

public interface iBoardGameService {
    public BoardGameResponseDTO getBoardGame(Long idBoardGame);
    public List<BoardGameResponseDTO> getAll();
    public BoardGameResponseDTO createBoardGame(BoardGameRequestDTO boardGameDTO);
    public String deleteBoardGame(Long idBoardGame);
    public BoardGameResponseDTO editBoardGame(Long idBoardGame, BoardGameRequestDTO boardGameDTO);
}
