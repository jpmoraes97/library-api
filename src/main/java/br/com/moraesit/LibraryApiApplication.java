package br.com.moraesit;

import br.com.moraesit.api.v1.mappers.BookMapper;
import br.com.moraesit.api.v1.models.BookDTO;
import br.com.moraesit.domain.models.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApiApplication.class, args);
    }

    @Bean
    public BookMapper bookMapper() {
        return new BookMapper() {
            @Override
            public Book bookDTOToBook(BookDTO bookDTO) {
                if (bookDTO == null) {
                    return null;
                }

                Book.BookBuilder book = Book.builder();

                book.id(bookDTO.getId());
                book.title(bookDTO.getTitle());
                book.author(bookDTO.getAuthor());
                book.isbn(bookDTO.getIsbn());

                return book.build();
            }

            @Override
            public BookDTO bookToBookDTO(Book book) {
                if (book == null) {
                    return null;
                }

                BookDTO.BookDTOBuilder bookDTO = BookDTO.builder();

                bookDTO.id(book.getId());
                bookDTO.title(book.getTitle());
                bookDTO.author(book.getAuthor());
                bookDTO.isbn(book.getIsbn());

                return bookDTO.build();
            }
        };
    }
}
