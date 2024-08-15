package com.example.emovies.service;

import com.example.emovies.model.UserModel;
import com.example.emovies.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepo repo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    BCryptPasswordEncoder crypt = new BCryptPasswordEncoder(12);

    @Override
    public UserModel addUser(UserModel user) {
        user.setUserPassword(crypt.encode(user.getUserPassword()));
        return repo.save(user);
    }

    @Override
    public String validateAndGenerateToken(UserModel user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUserName());
        }
        return "FAILED";
    }
}
