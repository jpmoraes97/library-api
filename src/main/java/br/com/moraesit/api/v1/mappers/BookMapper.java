package br.com.moraesit.api.v1.mappers;

import br.com.moraesit.api.v1.models.BookDTO;
import br.com.moraesit.domain.models.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    Book bookDTOToBook(BookDTO bookDTO);

    BookDTO bookToBookDTO(Book book);
}