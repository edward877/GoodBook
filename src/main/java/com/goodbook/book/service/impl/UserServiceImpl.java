package com.goodbook.book.service.impl;

import com.goodbook.book.dao.UserDao;
import com.goodbook.book.model.UserDto;
import com.goodbook.book.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

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
        if (!userDao.existsByEmail(userDto.getEmail())){
            validPassword(userDto.getSecretPassword());
            userDto.setPassword(passwordEncoder.encode(userDto.getSecretPassword()));
            userDao.save(userDto);
        } else {
            throw new SQLException("Username already exist");
        }
    }

    //TODO -  сделать нормальную валидацию пароля
    private void validPassword(String password) throws Exception{
        if (password.length() < 6){
            throw new Exception("Password not good");
        }
    }

    public Optional<UserDto> getUser() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object user = auth.getPrincipal();
        if (user instanceof UserDetails) {
            Optional<UserDto> userDto = userDao.findByEmail(((UserDetails)user).getUsername());
            return  userDto;
        }
        throw new Exception("User not log in");

    }


}
