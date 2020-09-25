package br.com.moraesit.domain.services.impl;

import br.com.moraesit.domain.exception.NegocioException;
import br.com.moraesit.domain.models.Book;
import br.com.moraesit.domain.repositories.BookRepository;
import br.com.moraesit.domain.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn()))
            throw new NegocioException("Isbn já cadastrado.");
        return bookRepository.save(book);
    }
}
