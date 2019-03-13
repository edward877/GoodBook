package com.goodbook.book.dao;

import com.goodbook.book.model.OrderBookDto;
import com.goodbook.book.model.OrderDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBookDao extends CrudRepository<OrderBookDto, Long> {

    List<OrderBookDto> findAllByOrder(OrderDto orderDto);
}
