package com.goodbook.book.service.impl;

import com.goodbook.book.dao.BookDao;
import com.goodbook.book.dao.CommentDao;
import com.goodbook.book.model.BookDto;
import com.goodbook.book.model.CommentDto;
import com.goodbook.book.service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    BookDao bookDao;

    @Override
    public Map addComment(CommentDto comment, int bookId) {
        Map<String, Object> map = new HashMap<>(1);
        Optional<BookDto> book = bookDao.findById(bookId);
        if (!book.isPresent()) {
            map.put("success", false);
            return map;
        }
        book.ifPresent(comment::setBook);
        if (commentDao.save(comment) != null) {
            map.put("success", true);
        } else {
            map.put("error", "Imposible save book");
        }
        return map;
    }
}
