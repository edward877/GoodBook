package com.goodbook.book.controller.api;

import com.goodbook.book.model.BookDto;
import com.goodbook.book.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/{page}")
    public List<BookDto> books(@PathVariable("page") String page) {
        return booksPage(page);
    }

    @GetMapping
    public List<BookDto> booksPage() {
        return booksPage("1");
    }

    @GetMapping("/book/{bookId}")
    public BookDto book(@PathVariable("bookId") String bookId) {
        return bookService.findOneById(Integer.parseInt(bookId));
    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public BookDto book(@ModelAttribute BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    private List<BookDto> booksPage(String page){
        return bookService.findAllBook(Integer.parseInt(page));
    }

}
