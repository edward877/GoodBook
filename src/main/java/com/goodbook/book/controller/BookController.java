package com.goodbook.book.controller;

import com.goodbook.book.controller.api.BookApi;
import com.goodbook.book.controller.api.CommentApi;
import com.goodbook.book.model.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping
public class BookController {

    @Autowired
    private BookApi api;
    @Autowired
    private CommentApi api2;

    @GetMapping("books")
    public ModelAndView booksPage(Integer page, Integer count, String direction, String sortProperty, String category) {
        if (page==null) {
            page = 1;
        }
        if(count==null) {
            count = 6;
        }
        Map model = api.booksPage(page, count, direction, sortProperty, category);
        return new ModelAndView("books", model);
    }

    @GetMapping("/books/book/{bookId}")
    public ModelAndView book(@PathVariable("bookId") int bookId) {
        Map model = api.book(bookId);
        return new ModelAndView("book", model);
    }

    @PostMapping("/books/book/comment")
    public  ModelAndView comment(@ModelAttribute CommentDto commentDto, int bookId) {
        api2.comment(commentDto, bookId);
        return book(bookId);

    }

}
