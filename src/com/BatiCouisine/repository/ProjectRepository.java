package com.BatiCouisine.repository;

import com.BatiCouisine.entities.Projet;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    int store(Projet projet, int idClient);
    Optional<Projet> findById(int id);
    void update(int id, Projet projet);
    void destroy(int id);
    HashMap<String, Projet> retrieveAll();
}
