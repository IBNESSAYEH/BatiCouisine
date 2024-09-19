package com.BatiCouisine.repository;

import com.BatiCouisine.entities.Devis;

import java.util.Optional;

public interface DevisRepositry {

    void store(Devis devis, int idProjt);
    //List<Devis> retrieveAllDevis();
    Optional<Devis> findById(int id);
    void update(int id, Devis devis);
    void destroy(int id);
}
