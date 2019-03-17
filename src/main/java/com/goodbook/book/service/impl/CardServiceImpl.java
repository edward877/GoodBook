package com.goodbook.book.service.impl;

import com.goodbook.book.dao.BookDao;
import com.goodbook.book.model.BookDto;
import com.goodbook.book.model.CardDto;
import com.goodbook.book.model.CardItemDto;
import com.goodbook.book.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        boolean success = false;
        if(book.isPresent()) {
            if (book.get().getCount() > cardDto.getCountOneBookInCard(bookId)) {
                cardDto.addBook(bookId);
                success = true;
            }
        }
        map.put("success", success);
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
        List<CardItemDto> books = new ArrayList<>();
        for(Map.Entry<Integer, Integer> cardItem : cardDto.getCard().entrySet()) {
            bookDao.findById(cardItem.getKey()).ifPresent(bookDto -> {
                CardItemDto cardItemDto = new CardItemDto();
                cardItemDto.setAuthor(bookDto.getAuthor());
                cardItemDto.setId(bookDto.getId());
                cardItemDto.setName(bookDto.getName());
                cardItemDto.setCountInCard(cardItem.getValue());
                cardItemDto.setUrlImage(bookDto.getUrlImage());
                cardItemDto.setPrice(Math.floor(bookDto.getPrice() * cardItem.getValue()));
                books.add(cardItemDto);
            });
        }
        if (books.size() > 0) {
            map.put("Books", books);
            map.put("CardPrice", books.stream().mapToDouble(CardItemDto::getPrice).sum());
        }
        return map;
    }
}
