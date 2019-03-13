package com.goodbook.book.dao;

import com.goodbook.book.model.OrderDto;
import com.goodbook.book.model.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao extends CrudRepository<OrderDto, Long> {

    Optional<OrderDto> findByIdAndUser(long id, UserDto userDto);

    List<OrderDto> findAllByUser(UserDto userDto);

}
