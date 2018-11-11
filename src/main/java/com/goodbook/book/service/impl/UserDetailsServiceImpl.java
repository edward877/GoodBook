package com.goodbook.book.service.impl;

import com.goodbook.book.model.UserDto;
import com.goodbook.book.dao.UserDao;
import com.goodbook.book.model.Enum.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userDao.findByUsername(username);
        if (userDto == null){
            throw new UsernameNotFoundException("User Name "+ username +"Not Found");
        }
        UserDetails user = new User(
                userDto.getUsername(),
                userDto.getPassword(),
                getAuthorities(UserRole.valueOf(userDto.getRole())));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthorities(UserRole role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>(1);
        authList.add(new SimpleGrantedAuthority(role.name()));
        return authList;
    }
}
