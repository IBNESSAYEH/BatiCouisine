package com.BatiCouisine.repository;

import com.BatiCouisine.entities.MainDoeuvre;

import java.util.List;
import java.util.Optional;

public interface MainDoeuvreRepository {

    void store(MainDoeuvre mainDoeuvre, int idProjt);
    Optional<MainDoeuvre> findById(int id);
    void update(int id, MainDoeuvre mainDoeuvre);
    void destroy(int id);
    List<MainDoeuvre> findAll();

}
