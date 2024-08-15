package com.example.emovies.service;


import com.example.emovies.model.UserModel;

public interface IUserService {
    UserModel addUser(UserModel user);

    String validateAndGenerateToken(UserModel user);
}
