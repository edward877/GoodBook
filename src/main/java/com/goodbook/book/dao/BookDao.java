package com.goodbook.book.dao;

import com.goodbook.book.model.BookDto;
import com.goodbook.book.model.Enum.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends CrudRepository<BookDto, Integer>, PagingAndSortingRepository<BookDto, Integer> {

//    List<BookDto> findAll();

    Page<BookDto> findAllByCategory(Pageable pageable, BookCategory category);


}
