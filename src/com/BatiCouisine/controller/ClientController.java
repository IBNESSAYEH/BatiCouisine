package com.BatiCouisine.controller;

import com.BatiCouisine.entities.Client;
import com.BatiCouisine.service.ClientService;

import java.util.Optional;
import java.util.Scanner;

public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public int index() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        int idClient = 0;
        do{
            menu();
            choix = 0;

            System.out.println("entrer votre choix :");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:{
                     idClient = findByName();
                     return idClient;

                }

                case 2:{
                    idClient =  store(new Client());;
                   return idClient;

                }
                default:
                    System.out.println("Veuillez choisir une option valide");
                    break;
            }


        }while(true);



    }

    public static void menu() {
        System.out.println("========= premierment creer un nouveau client ou choisir un client existant =========");
        System.out.println("==================================== Menu client =================================\n" +
                            "1. chercher un client existant :\n" +
                            "2. Ajouter un nouveau client :\n" +
                            "Veuillez choisir une option: \n");
    }

    public int store(Client client) {
        System.out.println("Entrez le nom du client : ");
        Scanner scanner = new Scanner(System.in);
        String nom = scanner.nextLine();
        client.setNom(nom);
        System.out.println("Entrez le numero de telephone du client : ");
        String numeroTelephone = scanner.nextLine();
        client.setNumeroTelephone(numeroTelephone);
        System.out.println("Entrez l'adresse du client : ");
        String address = scanner.nextLine();
        client.setAddress(address);
        System.out.println("Est-ce un client professionnel ? (true/false) : ");
        boolean estProfessionnelle = scanner.nextBoolean();
        client.setEstProfessionnelle(estProfessionnelle);
        int idGenerated = clientService.store(client);
        if(idGenerated != 0){
            return idGenerated;
        }else{
            System.out.println("Erreur lors de la creation du client vuillez creer le client de nouveau.");
            return 0;
        }
    }

    public int findByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du client : ");
        String nomClient = scanner.nextLine();

        Optional<Client> clientOptional= clientService.findByName(nomClient);
        if(clientOptional.isPresent()){
            System.out.println("Client trouver !");
            System.out.println("Nom du client : " + clientOptional.get().getNom());
            System.out.println("Numero de telephone : " + clientOptional.get().getNumeroTelephone());
            System.out.println("Adresse : " + clientOptional.get().getAddress());
            return clientOptional.get().getId();
        }else{
            return 0;
        }

    }

    public void update(int id, Client client) {
        clientService.update(id, client);
    }

    public void destroy(int id) {
        clientService.destroy(id);
    }

    public void findAll() {
        clientService.findAll();
    }
}
