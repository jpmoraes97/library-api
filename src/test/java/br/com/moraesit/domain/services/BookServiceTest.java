package br.com.moraesit.domain.services;

import br.com.moraesit.domain.exception.NegocioException;
import br.com.moraesit.domain.models.Book;
import br.com.moraesit.domain.repositories.BookRepository;
import br.com.moraesit.domain.services.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        this.bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest() {
        //cenario
        Book book = createValidBook();

        Mockito.when(bookRepository.existsByIsbn(Mockito.anyString())).thenReturn(false);

        Mockito.when(bookRepository.save(book)).thenReturn(
                Book.builder()
                        .id(1L)
                        .title("Javinha")
                        .author("João Pedro")
                        .isbn("123ABC").build());

        // execução
        Book savedBook = bookService.save(book);

        //verificação
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Javinha");
        assertThat(savedBook.getAuthor()).isEqualTo("João Pedro");
        assertThat(savedBook.getIsbn()).isEqualTo("123ABC");
    }

    @Test
    @DisplayName("Deve lançar erro de negócio ao tentar salvar um livro com isbn duplicado")
    public void shouldNotSaveABookWithDuplicatedIsbn() {

        //cenário
        Book book = createValidBook();

        Mockito.when(bookRepository.existsByIsbn(Mockito.anyString())).thenReturn(true);

        //execução
        Throwable exception = Assertions.catchThrowable(() -> bookService.save(book));

        //verificação
        assertThat(exception)
                .isInstanceOf(NegocioException.class)
                .hasMessage("Isbn já cadastrado.");

        Mockito.verify(bookRepository, Mockito.never()).save(book);
    }

    private Book createValidBook() {
        Book book = Book.builder()
                .title("Javinha")
                .author("João Pedro")
                .isbn("123ABC")
                .build();
        return book;
    }
}