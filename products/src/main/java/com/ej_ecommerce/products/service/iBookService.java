package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BookRequestDTO;
import com.ej_ecommerce.products.dto.response.BookResponseDTO;
import com.ej_ecommerce.products.model.Book;

import java.util.List;

public interface iBookService {
    public BookResponseDTO getBook(Long idBook);
    public List<BookResponseDTO> getAll();
    public BookResponseDTO createBook(BookRequestDTO bookDTO);
    public String deleteBook(Long idBook);
    public BookResponseDTO editBook(Long idBook, BookRequestDTO bookDTO);
}
