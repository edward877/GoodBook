package com.goodbook.book.controller;

import com.goodbook.book.controller.api.RegistrationApi;
import com.goodbook.book.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationApi api;

    @GetMapping("registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("registration")
    public ModelAndView showRegistrationPage(@ModelAttribute UserDto userDto){
        ModelAndView modelAndView;
        Map map = api.registration(userDto);
        if (map.containsKey("error")) {
            modelAndView = new ModelAndView("registration", map);
        } else {
            modelAndView = new ModelAndView("login", map);
        }
        return modelAndView;
    }
}
