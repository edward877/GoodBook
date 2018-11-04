package com.goodbook.book.security;

import com.goodbook.book.model.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserPrincipal {

    private UserDto userEntity;

    public MyUserPrincipal(UserDto userEntity) {
        this.userEntity = userEntity;
    }


//    authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//    authorities[0] = new SimpleGrantedAuthority(userEntity.getRole());
}
