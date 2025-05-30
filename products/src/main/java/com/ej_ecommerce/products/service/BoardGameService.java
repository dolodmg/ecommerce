package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BoardGameRequestDTO;
import com.ej_ecommerce.products.dto.response.BoardGameResponseDTO;
import com.ej_ecommerce.products.mapper.BoardGameMapper;
import com.ej_ecommerce.products.model.BoardGame;
import com.ej_ecommerce.products.model.Brand;
import com.ej_ecommerce.products.repository.BoardGameRepository;
import com.ej_ecommerce.products.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BoardGameService implements iBoardGameService {
    private final BoardGameRepository boardGameRepository;
    private final BrandRepository brandRepository;
    private final BoardGameMapper boardGameMapper;

    public BoardGameService(BoardGameRepository boardGameRepository, BrandRepository brandRepository, BoardGameMapper boardGameMapper) {
        this.boardGameRepository = boardGameRepository;
        this.brandRepository = brandRepository;
        this.boardGameMapper = boardGameMapper;
    }

    @Override
    public BoardGameResponseDTO getBoardGame(Long idBoardGame) {
        BoardGame boardGame = boardGameRepository.findById(idBoardGame)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el juego de mesa con id " + idBoardGame));
        return boardGameMapper.toDto(boardGame);
    }

    @Override
    public List<BoardGameResponseDTO> getAll() {
        List<BoardGame> boardGames = boardGameRepository.findAll();
        return boardGames.stream()
                .map(boardGameMapper::toDto)
                .toList();
    }

    @Override
    public BoardGameResponseDTO createBoardGame(BoardGameRequestDTO boardGameDTO) {
        BoardGame boardGame = boardGameMapper.toEntity(boardGameDTO);
        boardGame = boardGameRepository.save(boardGame);
        return boardGameMapper.toDto(boardGame);
    }

    @Override
    public String deleteBoardGame(Long idBoardGame) {
        try {
            boardGameRepository.deleteById(idBoardGame);
            return "El juego de mesa fue eliminado correctamente";
        } catch (Exception e) {
            return "No se pudo eliminar el juego de mesa" + e.getMessage();
        }
    }

    @Override
    public BoardGameResponseDTO editBoardGame(Long idBoardGame, BoardGameRequestDTO boardGameDTO) {
        BoardGame existing = boardGameRepository.findById(idBoardGame)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el juego de mesa con ID " + idBoardGame));

        existing.setName(boardGameDTO.getName());
        existing.setPrice(boardGameDTO.getPrice());
        existing.setStock(boardGameDTO.getStock());
        existing.setImageUrl(boardGameDTO.getImageUrl());
        existing.setDescription(boardGameDTO.getDescription());

        Brand brand = brandRepository.findById(boardGameDTO.getIdBrand())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la marca con ID " + boardGameDTO.getIdBrand()));
        existing.setBrand(brand);

        BoardGame updated = boardGameRepository.save(existing);
        return boardGameMapper.toDto(updated);
    }
}
