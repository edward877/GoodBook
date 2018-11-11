package com.goodbook.book.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value={"", "/"})
public class IndexController {

    public ModelAndView index(Authentication authentication) {
        Map model = new HashMap<String, String>();
//        model.put("test",  10);
        ModelAndView modelAndView = new ModelAndView("index", model);
        return modelAndView;
    }
}
