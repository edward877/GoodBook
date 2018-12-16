package com.goodbook.book.dao;

import com.goodbook.book.model.BookDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends CrudRepository<BookDto, Integer>, PagingAndSortingRepository<BookDto, Integer> {

    //   @Query("select article from Article article left join fetch article.topics where article.id =:id")
    List<BookDto> findAll();
}
