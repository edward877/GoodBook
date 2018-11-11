package com.goodbook.book.dao;

import com.goodbook.book.model.OrderBookDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookDao extends CrudRepository<OrderBookDto, Long> {
}
