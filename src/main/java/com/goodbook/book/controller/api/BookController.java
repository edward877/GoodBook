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
@RequestMapping(value = "/api/books/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * @param page - текущая страница,  по умолчанию 1
     * @param count - число на странице, по умолчанию по умолчанию 10
     * @param direction - направление, по умолчанию desc
     * @param sortProperty - свойство, по которому происхожит сортировка, по умолчанию - id
     * @return map - MaxPage, Books - найденные книги, Count, Page
     */
    @GetMapping
    public Map<String, Object> booksPage(String page, String count, String direction, String sortProperty,
                                         String... filterProperties) {
        if( page == null || page.trim().isEmpty()) {
            page = "1";
        }
        if (count == null || count.trim().isEmpty() || !count.matches("[0-9]*")){
            count = "10";
        }
        if (direction == null || direction.trim().isEmpty()) {
            direction = Sort.Direction.DESC.name();
        }
        if (sortProperty == null || sortProperty.trim().isEmpty()) {
            sortProperty = "id";
        }
        return bookService.findAllBook(Integer.parseInt(page), Integer.parseInt(count), direction, sortProperty);
    }

    @GetMapping("book/{bookId}")
    public Map book(@PathVariable("bookId") String bookId) {
        return bookService.findOneById(Integer.parseInt(bookId));
    }

    @PostMapping("admin/book")
    public Map book(@ModelAttribute BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PostMapping("admin/book/{bookId}")
    public Map bookUpdate(@PathVariable("bookId") String bookId, @ModelAttribute BookDto bookDto) {
        bookDto.setId(Integer.parseInt(bookId));
        return bookService.addBook(bookDto);
    }

}
