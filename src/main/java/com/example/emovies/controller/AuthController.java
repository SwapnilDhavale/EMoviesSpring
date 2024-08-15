package com.example.emovies.controller;

import com.example.emovies.model.UserModel;
import com.example.emovies.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    IUserService service;

    @PostMapping(value = "/add-user")
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {
        UserModel savedUser =  service.addUser(user);
        savedUser.setUserPassword("****");
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserModel user) {
        String token =  service.validateAndGenerateToken(user);
        return ResponseEntity.ok(token);
    }

}
