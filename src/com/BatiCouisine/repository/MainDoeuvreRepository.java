package com.BatiCouisine.repository;

import com.BatiCouisine.entities.MainDoeuvre;

import java.util.Optional;

public interface MainDoeuvreRepository {

    void store(MainDoeuvreRepository mainDoeuvre, int idProjt);
    Optional<MainDoeuvre> findById(int id);
    void update(int id, MainDoeuvreRepository mainDoeuvre);
    void destroy(int id);

}
