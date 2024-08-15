package com.example.emovies.config;

import com.example.emovies.model.UserModel;
import com.example.emovies.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = repo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not present");
        }

        return new UserPrincipal(user);
    }
}
