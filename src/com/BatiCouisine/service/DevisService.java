package com.BatiCouisine.service;

import com.BatiCouisine.entities.Devis;

import java.util.List;
import java.util.Optional;

public interface DevisService {
    boolean store(Devis devis, int idProject);
    Optional<Devis> findById(int id);
    boolean update(int id, Devis devis);
    boolean destroy(int id);
    List<Devis> findAll();
}
