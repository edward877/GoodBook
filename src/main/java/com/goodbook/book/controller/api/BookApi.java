package com.goodbook.book.controller.api;

import com.goodbook.book.model.BookDto;
import com.goodbook.book.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/")
public class BookApi {

    private final BookService bookService;

    @Autowired
    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * @param page - текущая страница,  по умолчанию 1
     * @param count - число на странице, по умолчанию по умолчанию 10
     * @param direction - направление, по умолчанию desc
     * @param sortProperty - свойство, по которому происхожит сортировка, по умолчанию - id
     * @return map - MaxPage, Books - найденные книги, Count, Page
     */
    @GetMapping("books/")
    public Map<String, Object> booksPage(Integer page, Integer count, String direction, String sortProperty,
                                         String category) {
        if (direction == null || direction.trim().isEmpty()) {
            direction = Sort.Direction.DESC.name();
        }
        if (sortProperty == null || sortProperty.trim().isEmpty()) {
            sortProperty = "id";
        }
        if(page == null || page == 0) {
            page = 1;
        }
        if(count == null || count == 0) {
            count = 1;
        }
        return bookService.findAllBook(page, count, direction, sortProperty, category);
    }

    @GetMapping("books/book/{bookId}")
    public Map book(@PathVariable("bookId") int bookId) {
        return bookService.findOneById(bookId);
    }

    @PostMapping("admin/books/book")
    public Map book(@ModelAttribute BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PostMapping("admin/books/book/{bookId}")
    public Map bookUpdate(@PathVariable("bookId") String bookId, @ModelAttribute BookDto bookDto) {
        bookDto.setId(Integer.parseInt(bookId));
        return bookService.addBook(bookDto);
    }

}
