package com.BatiCouisine.controller;

import com.BatiCouisine.entities.Materiau;
import com.BatiCouisine.service.Implementation.MateriauServiceImp;
import com.BatiCouisine.service.MateriauService;

import java.util.Scanner;

public class MateriauController {
    private MateriauService materiauService;

    public MateriauController(MateriauService materiauService) {
        this.materiauService = materiauService;
    }


    public Materiau store(int idProjet) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nom du materiau : ");
        String nom = scanner.nextLine();
        System.out.println("Entrer le type de composant : ");
        String typeComposant = scanner.nextLine();
        System.out.println("Entrer la quantite du materiau : ");
        int quantite = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Entrer le cout unitaire du materiau : ");
        double coutUnitaire = scanner.nextDouble();
        System.out.println("Entrer le cout de transport du materiau : ");
        double coutTransport = scanner.nextDouble();
        System.out.println("Entrer le coefficient de qualite du materiau : ");
        double coefficientQualite = scanner.nextDouble();
        Materiau materiau = new Materiau( 0,  nom,  typeComposant,  0.00,  coutTransport,  coefficientQualite,  quantite,  coutUnitaire);
         materiauService.store(materiau, idProjet);
        return materiau;
    }
}
