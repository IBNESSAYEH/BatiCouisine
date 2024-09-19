package com.BatiCouisine.service;

import com.BatiCouisine.entities.Devis;

import java.util.List;
import java.util.Optional;

public interface DevisService {
    void store(Devis devis, int idClient);
    Optional<Devis> findById(int id);
    void update(int id, Devis devis);
    void destroy(int id);
    List<Devis> findAll();
}
