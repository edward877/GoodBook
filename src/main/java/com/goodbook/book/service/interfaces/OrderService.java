package com.goodbook.book.service.interfaces;

import com.goodbook.book.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface OrderService {

    Map create();

    Map delete(long id);

    Map get(long id);

    Map getAll();

    ///
    Map getAllOrderByUser(UserDto user);

    Map getOrderByUserAndId(UserDto user, long id);

    Map setStatus(UserDto user, long id, String status);
}
