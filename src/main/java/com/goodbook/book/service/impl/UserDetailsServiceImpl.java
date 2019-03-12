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
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserDto> userDto = userDao.findByEmail(username);
        if (!userDto.isPresent()){
            throw new UsernameNotFoundException("User Name "+ username +"Not Found");
        }

        UserDetails user = new User(
                userDto.get().getEmail(),
                userDto.get().getSecretPassword(),
                getAuthorities(userDto.get().getRole()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthorities(UserRole role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>(1);
        authList.add(new SimpleGrantedAuthority(role.name()));
        return authList;
    }
}
