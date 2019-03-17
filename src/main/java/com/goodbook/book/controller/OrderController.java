package com.goodbook.book.controller;

import com.goodbook.book.controller.api.OrderApi;
import com.goodbook.book.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderApi api;

    @PostMapping("/order/")
    public ModelAndView add() {
        api.add();
        Map model = api.getAll();
        return new ModelAndView("orders", model);
    }

    @GetMapping("/order/")
    public ModelAndView getAll() {
        Map model = api.getAll();
        return new ModelAndView("orders", model);
    }

    @GetMapping("/order/{orderId}")
    public Map get(@PathVariable("orderId") Long orderId) {
        return api.get(orderId);
    }

}
