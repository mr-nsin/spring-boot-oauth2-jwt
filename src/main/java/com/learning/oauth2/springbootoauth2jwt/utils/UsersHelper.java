package com.learning.oauth2.springbootoauth2jwt.utils;

import org.springframework.security.core.userdetails.User;
import com.learning.oauth2.springbootoauth2jwt.models.UsersPojo;
public class UsersHelper extends User{

    private static final long serialVersionUID = 1L;
    public UsersHelper(UsersPojo user) {
        super(
                user.getUsername(),
                user.getPassword(),
                user.getListOfgrantedAuthorities()
        );
    }
}