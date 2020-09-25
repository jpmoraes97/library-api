package br.com.moraesit.api.v1.controllers;

import br.com.moraesit.api.v1.mappers.BookMapper;
import br.com.moraesit.api.v1.models.BookDTO;
import br.com.moraesit.domain.models.Book;
import br.com.moraesit.domain.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<BookDTO> create(@RequestBody @Valid BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book = bookService.save(book);
        return new ResponseEntity<>(bookMapper.bookToBookDTO(book), HttpStatus.CREATED);
    }
}