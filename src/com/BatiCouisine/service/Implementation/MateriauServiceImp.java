package com.BatiCouisine.service.Implementation;

import com.BatiCouisine.entities.MainDoeuvre;
import com.BatiCouisine.entities.Materiau;
import com.BatiCouisine.repository.MainDoeuvreRepository;
import com.BatiCouisine.repository.MateriauRepository;
import com.BatiCouisine.service.MainDoeuvreService;
import com.BatiCouisine.service.MateriauService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MateriauServiceImp implements MateriauService {
    private MateriauRepository materiauRepository;

    public MateriauServiceImp(MateriauRepository materiauRepository) {
        this.materiauRepository = materiauRepository;
    }

    public boolean store(Materiau materiau, int idProjt) {
        if (materiau == null) {
            throw new IllegalArgumentException("MainDoeuvre est null");
        }
        try {
            materiauRepository.store(materiau, idProjt);
            return true;
        } catch (Exception e) {
            System.err.println("Erreur pendant la creation du Main d'oeuvre: " + e.getMessage());
            return false;
        }
    }

    public boolean update(int id, Materiau materiau) {
        if (materiau == null) {
            throw new IllegalArgumentException("MainDoeuvre est null");
        }
        try {
            materiauRepository.update(id, materiau);
            return true;
        } catch (Exception e) {
            System.err.println("Error pendant la modification du main d'oeuvre: " + e.getMessage());
            return false;
        }
    }

    public boolean destroy(int id) {
        try {
            materiauRepository.destroy(id);
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du main d'oeuvre: " + e.getMessage());
            return false;
        }
    }

    public Optional<Materiau> findById(int id) {
        try {
            return materiauRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche du main d'oeuvre: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<Materiau> findAll() {
        try {
            return materiauRepository.findAll();
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche du main d'oeuvre: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
