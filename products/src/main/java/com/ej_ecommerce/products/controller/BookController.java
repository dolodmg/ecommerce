package com.ej_ecommerce.products.controller;

import com.ej_ecommerce.products.dto.request.BookRequestDTO;
import com.ej_ecommerce.products.dto.response.BookResponseDTO;
import com.ej_ecommerce.products.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Books Endpoints")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get/all")
    @Operation(summary = "Retrieve all the books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/get/{idProduct}")
    @Operation(summary = "Retrieve a book by its ID")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long idProduct) {
        BookResponseDTO book = bookService.getBook(idProduct);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/post")
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookDTO) {
        BookResponseDTO created = bookService.createBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/edit/{idProduct}")
    @Operation(summary = "Edit a book")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long idProduct, @RequestBody BookRequestDTO bookDTO) {
        BookResponseDTO updated = bookService.editBook(idProduct, bookDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{idProduct}")
    @Operation(summary = "Delete a book")
    public ResponseEntity<String> deleteBook(@PathVariable Long idProduct) {
        String message = bookService.deleteBook(idProduct);
        return ResponseEntity.ok(message);
    }
}
