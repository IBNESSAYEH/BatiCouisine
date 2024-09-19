package com.BatiCouisine.repository;

import com.BatiCouisine.entities.Projet;

import java.util.Optional;

public interface ProjectRepository {
    void store(Projet projet, int idClient);
    Optional<Projet> findById(int id);
    void update(int id, Projet projet);
    void destroy(int id);
}
