package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.BookRequestDTO;
import com.ej_ecommerce.products.dto.response.BookResponseDTO;
import com.ej_ecommerce.products.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/get/{idBook}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long idBook) {
        BookResponseDTO book = bookService.getBook(idBook);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/post")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookDTO) {
        BookResponseDTO created = bookService.createBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/edit/{idBook}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long idBook, @RequestBody BookRequestDTO bookDTO) {
        BookResponseDTO updated = bookService.editBook(idBook, bookDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{idBook}")
    public ResponseEntity<String> deleteBook(@PathVariable Long idBook) {
        String message = bookService.deleteBook(idBook);
        return ResponseEntity.ok(message);
    }
}
