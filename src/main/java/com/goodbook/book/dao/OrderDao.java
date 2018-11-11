package com.goodbook.book.dao;

import com.goodbook.book.model.OrderDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends CrudRepository<OrderDto, Long> {
}
