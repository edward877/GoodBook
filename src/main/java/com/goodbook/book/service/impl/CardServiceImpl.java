package com.goodbook.book.service.impl;

import com.goodbook.book.dao.BookDao;
import com.goodbook.book.model.BookDto;
import com.goodbook.book.model.CardDto;
import com.goodbook.book.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDto cardDto;

    @Autowired
    private BookDao bookDao;

    @Override
    public Map add(int bookId) {
        Map<String, Object> map = new HashMap<>();
        Optional<BookDto> book = bookDao.findById(bookId);
        book.ifPresent(bookDto -> cardDto.addBook(bookId));
        map.put("success", book.isPresent());
        return map;
    }

    @Override
    public Map delete(int bookId) {
        Map<String, Object> map = new HashMap<>();
        Optional<BookDto> book = bookDao.findById(bookId);
        boolean success = false;
        if(book.isPresent()) {
            success = cardDto.removeBook(bookId);
        }
        map.put("success", success);
        return map;
    }

    @Override
    public Map minus(int bookId) {
        Map<String, Object> map = new HashMap<>();
        Optional<BookDto> book = bookDao.findById(bookId);
        boolean success = false;
        if(book.isPresent()) {
            success = cardDto.decrementCount(bookId);
        }
        map.put("success", success);
        return map;
    }

    @Override
    public Map getAll() {
        Map<String, Object> map = new HashMap<>();
        List<Map> books = new ArrayList<>();
        for(Map.Entry<Integer, Integer> cardItem : cardDto.getCard().entrySet()) {
            bookDao.findById(cardItem.getKey()).ifPresent(bookDto -> {
                Map<String, Object> bookMap = new HashMap<>();
                bookMap.put("id", bookDto.getId());
                bookMap.put("name", bookDto.getName());
                bookMap.put("author", bookDto.getAuthor());
                bookMap.put("countInCard", cardItem.getValue());
                bookMap.put("urlImage", bookDto.getUrlImage());
                bookMap.put("price", bookDto.getPrice() * cardItem.getValue());
                books.add(bookMap);
            });
        }
        map.put("Books", books);
        return map;
    }
}
