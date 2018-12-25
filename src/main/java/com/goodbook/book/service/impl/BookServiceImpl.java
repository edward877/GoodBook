package com.goodbook.book.service.impl;

import com.goodbook.book.dao.BookDao;
import com.goodbook.book.model.BookDto;
import com.goodbook.book.model.Enum.BookCategory;
import com.goodbook.book.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public Map<String, Object> findOneById(int id) throws NullPointerException {
        Map<String, Object> map = new HashMap<>(1);
        BookDto book = bookDao.findById(id).get();
        if (book != null){
            map.put("Book", book);
        } else {
            map.put("Error", "Book not found");
        }
        return map;
    }

    @Override
    public Map<String, Object> addBook(BookDto bookDto) {
        Map<String, Object> map = new HashMap<>(1);
        if (bookDao.save(bookDto) != null) {
            map.put("ok", true);
        } else {
            map.put("error", "Imposible save book");
        }
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
        } else  if (countInPage < 0) {
            countInPage = 1;
        }

        int maxBookPage = (int)Math.ceil((double)bookDao.findAll().size()/countInPage);

        if (page > maxBookPage) {
            page = maxBookPage;
        } else  if (page < 1) {
            page = 1;
        }

        Sort sort =  Sort.by(Sort.Direction.fromString(direction), property);
        Pageable pageable = PageRequest.of(page-1, countInPage, sort);

        List<BookDto> books = bookDao.findAll(pageable).getContent();
        if (books != null) {
            map.put("MaxPage", maxBookPage);
            map.put("Books", books);
            map.put("Count", books.size());
            map.put("Page", page);
            map.put("BookCategories", BookCategory.values());
        } else {
            map.put("Error", "Книги не найдены");
        }

        return  map;
    }
//
//    public static BookCategory[] getBooksCategoty() {
//        return ;
//    }

}
