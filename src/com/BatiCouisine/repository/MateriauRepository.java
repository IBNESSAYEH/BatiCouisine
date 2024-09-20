package com.BatiCouisine.repository;

import com.BatiCouisine.entities.MainDoeuvre;
import com.BatiCouisine.entities.Materiau;

import java.util.List;
import java.util.Optional;

public interface MateriauRepository {

    void store(Materiau materiau, int idProjt);
    Optional<Materiau> findById(int id);
    void update(int id, Materiau materiau);
    void destroy(int id);
    List<Materiau> findAll();

}
