package com.goodbook.book.dao;

import com.goodbook.book.model.CommentDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends CrudRepository<CommentDto, Long> {
}
