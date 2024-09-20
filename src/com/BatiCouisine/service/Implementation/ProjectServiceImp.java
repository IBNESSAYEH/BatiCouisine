package com.BatiCouisine.service.Implementation;

import com.BatiCouisine.entities.Projet;
import com.BatiCouisine.repository.implementation.ProjectRepositoryImp;
import com.BatiCouisine.service.ProjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProjectServiceImp implements ProjectService {

    private ProjectRepositoryImp projectRepository;

    public ProjectServiceImp(ProjectRepositoryImp projectRepository) {
        this.projectRepository = projectRepository;
    }

    public boolean store(Projet projet, int idClient) {
        if (projet == null || projet.getNom() == null) {
            System.out.println("les donnees du projet sont invalides");
            return false;
        }
        try {
            projectRepository.store(projet, idClient);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(int id, Projet projet) {
        if (projet == null || projet.getNom() == null) {
            System.out.println("les donnees du projet sont invalides");
            return false;
        }
        try {
            projectRepository.update(id, projet);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean destroy(int id) {
        try {
            projectRepository.destroy(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Projet> findById(int id) {
        try {
            return projectRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public HashMap<String, Projet> retrieveAll() {
        try {
            return projectRepository.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
