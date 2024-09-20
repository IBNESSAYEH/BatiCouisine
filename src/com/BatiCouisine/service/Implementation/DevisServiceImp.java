package com.BatiCouisine.service.Implementation;

import com.BatiCouisine.entities.Client;
import com.BatiCouisine.entities.Devis;
import com.BatiCouisine.repository.implementation.DevisRepositoryImp;
import com.BatiCouisine.service.DevisService;
import com.BatiCouisine.util.DBUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DevisServiceImp implements DevisService {
    private DevisRepositoryImp devisRepository;

    public DevisServiceImp(DevisRepositoryImp devisRepository) {
        this.devisRepository = devisRepository;
    }

    public boolean store(Devis devis, int idProjt) {
        if (devis == null || devis.getDateMission() == null ) {
            System.out.println("les donnees du client sont invalides");
            return false;
        }
        try {
            devisRepository.store(devis, idProjt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(int id, Devis devis) {
        if (devis == null || devis.getDateMission() == null ) {
            System.out.println("les donnees du client sont invalides");
            return false;
        }
        try {
            devisRepository.update(id, devis);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean destroy(int id) {
        try {
            devisRepository.destroy(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Devis> findById(int id) {

        try {
            return devisRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Devis> findAll() {
        try {
            return devisRepository.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
        }



}
