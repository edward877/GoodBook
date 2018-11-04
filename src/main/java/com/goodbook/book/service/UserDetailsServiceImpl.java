package com.goodbook.book.service;

import com.goodbook.book.model.UserDto;
import com.goodbook.book.dao.UserDao;
import com.goodbook.book.security.Enum.UserRole;
import com.goodbook.book.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userDao.findByUsername(username);
        if (userDto == null){
            throw new UsernameNotFoundException("User Name "+ username +"Not Found");
        }
        UserDetails user = new User(
                userDto.getUsername(), userDto.getPassword(), getAuthorities(userDto.getRole()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthorities(UserRole role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>(1);
        authList.add(new SimpleGrantedAuthority(role.name()));
        return authList;
    }

////        create password
//        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        userDao.save(userDto);
////
}
