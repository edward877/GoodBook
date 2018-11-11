package com.goodbook.book.service.impl;

import com.goodbook.book.dao.BookDao;
import com.goodbook.book.model.BookDto;
import com.goodbook.book.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    private static final int countInPage = 1;

    @Override
    public BookDto findOneById(int id) throws NullPointerException {
        return bookDao.findById(id).orElseThrow(() -> new NullPointerException("Book not found"));
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        return bookDao.save(bookDto);
    }

    @Override
    public BookDto deleteBook(int id) {
        return null;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return null;
    }

    @Override
    public List<BookDto> findAllBook(int page) {
        //TODO изменить Sort на новую версию, и вынести Sort.Order параметры в getter, чтобы можно было сортировать разными способами
        @Deprecated
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = new PageRequest(page-1, countInPage, sort);
        return bookDao.findAll(pageable).getContent();
    }



}
