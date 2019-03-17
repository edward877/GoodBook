package com.goodbook.book.controller;

import com.goodbook.book.controller.api.BookApi;
import com.goodbook.book.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private BookApi api;

    @GetMapping(value={"", "/", "/index/"})
    public ModelAndView index() {
        Map model = api.booksPage(1, 10, "desc", "id", null);
        return new ModelAndView("index", model);
    }
}
