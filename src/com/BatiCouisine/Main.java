package com.BatiCouisine;

import com.BatiCouisine.controller.ClientController;
import com.BatiCouisine.repository.ClientRepository;
import com.BatiCouisine.repository.implementation.ClientRepositoryImp;
import com.BatiCouisine.service.ClientService;
import com.BatiCouisine.service.Implementation.ClientServiceImp;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ClientRepository clientRepository = new ClientRepositoryImp();
        ClientService clientService = new ClientServiceImp(clientRepository);
        ClientController clientController = new ClientController(clientService);

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        /* ce Menu est lemenu principale de l'application
        1. Créer un nouveau projet : cette option permet d'executer un autre menu implementer dans le controlleur ClientController
           qui est l'etape une pour la creation d'un projet puis apres la creation du client ou choisir uncliant existant on switch a l'etape
           de cfreation du projet et ses composants et son devis ses dernoier option sont implementer dans le controlleur ProjectController
        2. Afficher les projets existants : pour les autre option 2 et 3 vas juste appeller deux methode du controlleur ProjectController
         */

        System.out.println("=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");

    do {
        mainMenu();
        System.out.println(">>>>>>   Veuillez choisir une option:   <<<<<<");
        choix = scanner.nextInt();
        scanner.nextLine();
        switch (choix) {
            case 1:
                clientController.index();
                break;
            case 2:
                System.out.println("affichage des projets existants");
                break;
            case 3:
                System.out.println("calcul du coût d'un projet");
                break;
            case 4:
                System.out.println("Merci d'avoir utilisé notre application");
                break;
            default:
                System.out.println("Veuillez choisir une option valide");
                break;
        }
    }while(choix != 4);


    }


    public static void mainMenu(){
        System.out.println("==================================== Menu Principal =================================\n" +
                "1. Créer un nouveau projet :\n" +
                "2. Afficher les projets existants :\n" +
                "3. Calculer le coût d'un projet :\n" +
                "4. Quitter :\n" +
                "=====================================================================================\n");
    }
}