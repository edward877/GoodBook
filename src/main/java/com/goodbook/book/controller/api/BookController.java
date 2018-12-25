package com.goodbook.book.controller.api;

import com.goodbook.book.model.BookDto;
import com.goodbook.book.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public Map<String, Object> booksPage(String page, String count, String direction, String property) {
        if( page == null || page.trim().isEmpty()) {
            page = "1";
        }
        if (count == null || count.trim().isEmpty()) {
            count = "10";
        }
        if (direction == null || direction.trim().isEmpty()) {
            direction = "desc";
        }
        if (property == null || property.trim().isEmpty()) {
            property = "id";
        }
        return bookService.findAllBook(Integer.parseInt(page), Integer.parseInt(count), direction, property);
    }

    @GetMapping("/book/{bookId}")
    public Map book(@PathVariable("bookId") String bookId) {
        return bookService.findOneById(Integer.parseInt(bookId));
    }

    @PostMapping("/book")
    public Map book(@ModelAttribute BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PostMapping("/book/{bookId}")
    public Map bookUpdate(@PathVariable("bookId") String bookId, @ModelAttribute BookDto bookDto) {
        bookDto.setId(Integer.parseInt(bookId));
        return bookService.addBook(bookDto);
    }

}
