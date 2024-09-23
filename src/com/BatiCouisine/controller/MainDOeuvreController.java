package com.BatiCouisine.controller;

import com.BatiCouisine.entities.MainDoeuvre;
import com.BatiCouisine.service.MainDoeuvreService;

import java.util.Scanner;

public class MainDOeuvreController {
    private MainDoeuvreService mainDoeuvreService;

    public MainDOeuvreController(MainDoeuvreService mainDoeuvreService){
        this.mainDoeuvreService = mainDoeuvreService;
    }


    public MainDoeuvre store(int idProject){
       // (int id, String nom, String typeComposant, Double tauxTVA, double tauxHoraire, double heurTravail, double productiviteOuvrier)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nom de la main d'oeuvre : ");
        String nom = scanner.nextLine();
        System.out.println("Entrer le type de composant : ");
        String typeComposant = scanner.nextLine();
        System.out.println("Entrer le taux horaire : ");
        double tauxHoraire = scanner.nextDouble();
        System.out.println("Entrer le nombre d'heures de travail : ");
        double heuresTravail = scanner.nextDouble();
        System.out.println("Entrer la productivite de l'ouvrier : ");
        double productiviteOuvrier = scanner.nextDouble();

        MainDoeuvre mainDoeuvre = new MainDoeuvre(0, nom, typeComposant, 0.00, tauxHoraire, heuresTravail, productiviteOuvrier);
         mainDoeuvreService.store(mainDoeuvre, idProject);
        return mainDoeuvre;
    }

}