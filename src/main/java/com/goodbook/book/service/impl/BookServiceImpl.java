package com.goodbook.book.service.impl;

import com.goodbook.book.dao.BookDao;
import com.goodbook.book.model.BookDto;
import com.goodbook.book.model.Enum.BookCategory;
import com.goodbook.book.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public Map<String, Object> findOneById(int id) throws NullPointerException {
        Map<String, Object> map = new HashMap<>();
        Optional<BookDto> book = bookDao.findById(id);
        if (book.isPresent()){
            map.put("Book", book.get());
            map.put("Comments", book.get()._getComents());
        } else {
            map.put("Error", "Book not found");
        }
        return map;
    }

    @Override
    public Map<String, Object> addBook(BookDto bookDto) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("success", bookDao.save(bookDto) != null);
        return map;
    }

    @Override
    public BookDto deleteBook(int id) {
        return null;
    }

    @Override
    public Map<String, Object> findAllBook(int page, int countInPage, String direction, String property) {
        Map<String, Object> map = new HashMap<>(4);

        if (countInPage > 50) {
            countInPage = 50;
        } else  if (countInPage < 1) {
            countInPage = 1;
        }

        if (page < 1) {
            page = 1;
        }

        Sort sort =  Sort.by(Sort.Direction.fromString(direction), property);
        Pageable pageable = PageRequest.of(page-1, countInPage, sort);

        Page pageList =  bookDao.findAll(pageable);
        List<BookDto> books = pageList.getContent();
        int maxBookPage = pageList.getTotalPages();


        if (books != null) {
            map.put("MaxPage", maxBookPage);
            map.put("Books", books);
            map.put("Count", books.size());
            map.put("Page", page);
        } else {
            map.put("Error", "Книги не найдены");
        }

        return  map;
    }

}
