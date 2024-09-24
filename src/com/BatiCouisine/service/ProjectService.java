package com.BatiCouisine.service;

import com.BatiCouisine.entities.Projet;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    int store(Projet projet, int idClient);
    Optional<Projet> findById(int id);
    boolean update(int id, Projet projet);
    boolean destroy(int id);
    HashMap<String, Projet> retrieveAll();
}
