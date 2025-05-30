package com.ej_ecommerce.products.service;

import com.ej_ecommerce.products.dto.request.BookRequestDTO;
import com.ej_ecommerce.products.dto.response.BookResponseDTO;
import com.ej_ecommerce.products.mapper.BookMapper;
import com.ej_ecommerce.products.model.Book;
import com.ej_ecommerce.products.model.Editorial;
import com.ej_ecommerce.products.model.Person;
import com.ej_ecommerce.products.repository.BookRepository;
import com.ej_ecommerce.products.repository.EditorialRepository;
import com.ej_ecommerce.products.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService implements iBookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final EditorialRepository editorialRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, PersonRepository personRepository, EditorialRepository editorialRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
        this.editorialRepository = editorialRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDTO getBook(Long idBook) {
        Book book = bookRepository.findById(idBook).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el libro con id " + idBook));
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookResponseDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public String deleteBook(Long idBook) {
        try {
            bookRepository.deleteById(idBook);
            return "El libro fue eliminado correctamente";
        } catch (Exception e) {
            return "No se pudo eliminar el libro" + e.getMessage();
        }
    }

    @Override
    public BookResponseDTO editBook(Long idBook, BookRequestDTO bookDTO) {
        Book existing = bookRepository.findById(idBook)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el libro con ID " + idBook));

        existing.setISBN(bookDTO.getISBN());
        existing.setTitle(bookDTO.getTitle());
        existing.setPublicationDate(bookDTO.getPublicationDate());
        existing.setGenre(bookDTO.getGenre());
        existing.setPrice(bookDTO.getPrice());
        existing.setStock(bookDTO.getStock());
        existing.setImageUrl(bookDTO.getImageUrl());
        existing.setDescription(bookDTO.getDescription());

        Editorial editorial = editorialRepository.findById(bookDTO.getIdEditorial())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la editorial con ID " + bookDTO.getIdEditorial()));
        existing.setEditorial(editorial);


        Person person = personRepository.findById(bookDTO.getIdPerson())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la persona con ID " + bookDTO.getIdPerson()));
        existing.setPerson(person);

        Book updated = bookRepository.save(existing);
        return bookMapper.toDto(updated);
    }
}
