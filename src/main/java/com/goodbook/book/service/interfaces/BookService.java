package com.goodbook.book.service.interfaces;

import com.goodbook.book.model.BookDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookService {

    Map findOneById(int id) throws NullPointerException ;

    Map<String, Object> addBook(BookDto bookDto);

    BookDto deleteBook(int id);

    BookDto updateBook(BookDto bookDto);

    Map<String, Object> findAllBook(int page, int countInPage, String direction, String property);

}
