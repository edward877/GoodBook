package com.goodbook.book.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value={"", "/", "index/"})
public class IndexController {

    public ModelAndView index(Authentication authentication) {
        Map model = new HashMap<String, String>();
        return  new ModelAndView("index", model);
    }
}
