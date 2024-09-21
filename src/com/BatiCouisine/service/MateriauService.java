package com.BatiCouisine.service;

import com.BatiCouisine.entities.MainDoeuvre;
import com.BatiCouisine.entities.Materiau;

import java.util.List;
import java.util.Optional;

public interface MateriauService {

    boolean store(Materiau materiau, int idProjt);
    boolean update(int id, Materiau materiau);
    boolean destroy(int id);
    Optional<Materiau> findById(int id);
    List<Materiau> findAll();
}
