package com.goodbook.book.controller;

import com.goodbook.book.controller.api.CardApi;
import com.goodbook.book.controller.api.RegistrationApi;
import com.goodbook.book.model.CardItemDto;
import com.goodbook.book.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "card/")
public class CardController {

    @Autowired
    private CardApi api;

    @GetMapping("")
    public ModelAndView get() {
        Map<String, List> model = api.get();
        return new ModelAndView("card", model);
    }

    @PostMapping("/add")
    public ModelAndView add(int id) {
        boolean success = (boolean) api.add(id).get("success");
        Map model = api.get();
        if(!success) {
            model.put("error", "Это максимум книг, который у нас есть");
        }
        return new ModelAndView("card", model);
    }

    @PostMapping("/minus")
    public ModelAndView minus(int id) {
        api.minus(id);
        Map model = api.get();
        return new ModelAndView("card", model);
    }
}
