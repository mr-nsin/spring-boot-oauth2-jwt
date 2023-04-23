package com.learning.oauth2.springbootoauth2jwt.services;

import com.learning.oauth2.springbootoauth2jwt.models.UsersPojo;
import com.learning.oauth2.springbootoauth2jwt.repositories.UsersDBQuery;
import com.learning.oauth2.springbootoauth2jwt.utils.UsersHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UsersDBQuery usersDBQuery;

    @Override
    public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
        UsersPojo usersPojo = null;
        try {
            usersPojo = usersDBQuery.getUserDetails(username);
            UsersHelper userDetail = new UsersHelper(usersPojo);
            return userDetail;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }

}
