package com.goodbook.book.service.interfaces;

import com.goodbook.book.model.BookDto;
import com.goodbook.book.model.CommentDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CommentService {

    Map addComment(CommentDto comment, int bookId);

}
