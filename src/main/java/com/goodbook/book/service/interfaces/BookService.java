package com.goodbook.book.service.interfaces;

import com.goodbook.book.model.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    BookDto findOneById(int id) throws NullPointerException ;

    BookDto addBook(BookDto bookDto);

    BookDto deleteBook(int id);

    BookDto updateBook(BookDto bookDto);

    List<BookDto> findAllBook(int page);

}
