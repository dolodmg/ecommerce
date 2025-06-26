package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BookRequestDTO;
import com.ej_ecommerce.products.dto.response.BookResponseDTO;

import java.util.List;

public interface iBookService {
    BookResponseDTO getBook(Long idBook);
    List<BookResponseDTO> getAll();
    BookResponseDTO createBook(BookRequestDTO bookDTO);
    String deleteBook(Long idBook);
    BookResponseDTO editBook(Long idBook, BookRequestDTO bookDTO);
}
