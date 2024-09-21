package com.BatiCouisine.service;

import com.BatiCouisine.entities.MainDoeuvre;

import java.util.List;
import java.util.Optional;

public interface MainDoeuvreService {

    boolean store(MainDoeuvre mainDoeuvre, int idProjt);
    boolean update(int id, MainDoeuvre mainDoeuvre);
    boolean destroy(int id);
    Optional<MainDoeuvre> findById(int id);
    List<MainDoeuvre> findAll();
    
}
