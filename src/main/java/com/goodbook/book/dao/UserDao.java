package com.goodbook.book.dao;

import com.goodbook.book.model.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<UserDto, Long> {

    Optional<UserDto> findByEmail(String email);

    boolean existsByEmail(String email);

}
