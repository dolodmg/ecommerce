package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BookRequestDTO;
import com.ej_ecommerce.products.dto.response.BookResponseDTO;

import java.util.List;

public interface iBookService {
    BookResponseDTO getBook(Long idProduct);
    List<BookResponseDTO> getAll();
    BookResponseDTO createBook(BookRequestDTO bookDTO);
    String deleteBook(Long idProduct);
    BookResponseDTO editBook(Long idProduct, BookRequestDTO bookDTO);
}
