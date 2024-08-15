package com.example.emovies.repository;

import com.example.emovies.model.EmovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmovieRepo extends JpaRepository<EmovieModel, Integer> {
    public EmovieModel findEmovieModelById(int id);
}
