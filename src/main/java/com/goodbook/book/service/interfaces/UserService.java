package com.goodbook.book.service.interfaces;

import com.goodbook.book.model.UserDto;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    void registration(UserDto userDto) throws Exception;
}
