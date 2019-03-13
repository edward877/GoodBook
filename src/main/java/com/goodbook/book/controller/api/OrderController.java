package com.goodbook.book.controller.api;

import com.goodbook.book.service.interfaces.CardService;
import com.goodbook.book.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("user/order/add")
    public Map add() {
        return orderService.create();
    }

    @PostMapping("user/order/delete/{orderId}")
    public Map delete(@PathVariable("orderId") Long orderId) {
        return orderService.delete(orderId);
    }

    @GetMapping("user/order/")
    public Map getAll() {
        return orderService.getAll();
    }

    @GetMapping("user/order/{orderId}")
    public Map get(@PathVariable("orderId") Long orderId) {
        return orderService.get(orderId);
    }
    //todo
    // можно добавить админские контроллеры - просмотр списка заказов юзера,
    // просмотр одного заказа юзера
    // замена статуса заказа юзера


}
