package com.goodbook.book.controller.api;

import com.goodbook.book.model.BookDto;
import com.goodbook.book.service.interfaces.BookService;
import com.goodbook.book.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/user/card/")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("add")
    public Map add(String id) {
        return cardService.add(Integer.parseInt(id));
    }

    @PostMapping("delete")
    public Map delete(int id) {
        return cardService.delete(id);
    }

    @PostMapping("minus")
    public Map minus(int id) {
        return cardService.minus(id);
    }

    @GetMapping("")
    public Map get() {
        return cardService.getAll();
    }

}
