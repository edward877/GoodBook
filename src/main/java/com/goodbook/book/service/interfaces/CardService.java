package com.goodbook.book.service.interfaces;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CardService {

    Map add(int bookId);

    Map delete(int bookId);

    Map minus(int bookId);

    Map getAll();
}
