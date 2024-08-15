package com.example.emovies.repository;

import com.example.emovies.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {
    public UserModel findByUserName(String userName);
}
