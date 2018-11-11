package com.goodbook.book.dao;

import com.goodbook.book.model.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserDto, Long> {

    UserDto findByUsername(String username);

    boolean existsByUsername(String username);

}
