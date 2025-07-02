package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BoardGameRequestDTO;
import com.ej_ecommerce.products.dto.response.BoardGameResponseDTO;

import java.util.List;

public interface iBoardGameService {
    BoardGameResponseDTO getBoardGame(Long idProduct);
    List<BoardGameResponseDTO> getAll();
    BoardGameResponseDTO createBoardGame(BoardGameRequestDTO boardGameDTO);
    String deleteBoardGame(Long idProduct);
    BoardGameResponseDTO editBoardGame(Long idProduct, BoardGameRequestDTO boardGameDTO);
}
