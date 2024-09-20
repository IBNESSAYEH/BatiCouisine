package com.BatiCouisine.service.Implementation;

import com.BatiCouisine.entities.MainDoeuvre;
import com.BatiCouisine.repository.MainDoeuvreRepository;
import com.BatiCouisine.service.MainDoeuvreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainDoeuvreServiceImp implements MainDoeuvreService {
    private MainDoeuvreRepository mainDoeuvreRepository;

    public MainDoeuvreServiceImp(MainDoeuvreRepository mainDoeuvreRepository) {
        this.mainDoeuvreRepository = mainDoeuvreRepository;
    }

    public boolean store(MainDoeuvre mainDoeuvre, int idProjt) {
        if (mainDoeuvre == null) {
            throw new IllegalArgumentException("MainDoeuvre est null");
        }

        try {
            mainDoeuvreRepository.store(mainDoeuvre, idProjt);
            return true;
        } catch (Exception e) {
            System.err.println("Erreur pendant la creation du Main d'oeuvre: " + e.getMessage());
            return false;
        }
    }

    public boolean update(int id, MainDoeuvre mainDoeuvre) {
        if (mainDoeuvre == null) {
            throw new IllegalArgumentException("MainDoeuvre est null");
        }

        try {
            mainDoeuvreRepository.update(id, mainDoeuvre);
            return true;
        } catch (Exception e) {
            System.err.println("Error pendant la modification du main d'oeuvre: " + e.getMessage());
            return false;
        }
    }

    public boolean destroy(int id) {
        try {
            mainDoeuvreRepository.destroy(id);
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du main d'oeuvre: " + e.getMessage());
            return false;
        }
    }

    public Optional<MainDoeuvre> findById(int id) {
        try {
            return mainDoeuvreRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche du main d'oeuvre: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<MainDoeuvre> findAll() {
        try {
            return mainDoeuvreRepository.findAll();
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche des main d'oeuvre: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
