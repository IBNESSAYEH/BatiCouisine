package com.BatiCouisine.controller;

import com.BatiCouisine.entities.EtatProject;
import com.BatiCouisine.entities.Projet;
import com.BatiCouisine.service.ProjectService;

import java.util.HashMap;
import java.util.Scanner;

public class ProjectController {
    private ProjectService projectService;



    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public Projet store(int idClient) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nom du projet : ");
        String nom = scanner.nextLine();
        System.out.println("Entrer la surface de la cuisine : ");
        double surfaceCuisine = scanner.nextDouble();
        Projet projet = new Projet(0, nom, 0, 0, EtatProject.EN_COURS, surfaceCuisine);
        int idProjet = projectService.store(projet, idClient);
        projet.setId(idProjet);
        return projet;
    }

    public void findById(int id) {
        projectService.findById(id);
    }

    public void update(int id, Projet projet) {
        projectService.update(id, projet);
    }

    public void destroy(int id) {
        projectService.destroy(id);
    }

    public void findAll() {
        HashMap<String, Projet>  projetHashMap= projectService.retrieveAll();
        for (Projet projet : projetHashMap.values()) {
            System.out.println("_________________________________________________________" +
                    "\nId : " + projet.getId() +
                    "\nNom : " + projet.getNom() +
                    "\nMarge bénéficiaire : " + projet.getMargeBeneficiaire() +
                    "\nCout total : " + projet.getCoutTotal() +
                    "\nEtat : " + projet.getEtat() +
                    "\nSurface cuisine : " + projet.getSurfaceCouisine() +
                    "\n_________________________________________________________");
        }
    }

}
