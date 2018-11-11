package com.goodbook.book.service.impl;

import com.goodbook.book.dao.UserDao;
import com.goodbook.book.model.UserDto;
import com.goodbook.book.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void registration(UserDto userDto) throws Exception{
        if (!userDao.existsByUsername(userDto.getUsername())){
            validPassword(userDto.getPassword());
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDao.save(userDto);
        } else {
            throw new SQLException("Username already exist");
        }
    }

    //TODO -  сделать нормальную валидацию пароля
    private boolean validPassword(String password) throws Exception{
        if(password.length() > 6){
            return true;
        }else{
            throw new Exception("Password not good");
        }
    }
}
