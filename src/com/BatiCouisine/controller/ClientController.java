package com.BatiCouisine.controller;

import com.BatiCouisine.entities.Client;
import com.BatiCouisine.service.ClientService;
import com.BatiCouisine.util.ValidationUtils;

import java.util.Optional;
import java.util.Scanner;

public class ClientController {
    private ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public Optional<Client> index() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        int idClient = 0;

            menu();
            choix = 0;
        Optional<Client>  OptionalClient = null;

            System.out.println("entrer votre choix :");
            choix = ValidationUtils.getValidInput(scanner);

            switch (choix) {
                case 1:{
                    OptionalClient= findByName();
                    return OptionalClient;
                }

                case 2:{
                    OptionalClient =  store(new Client());
                   return OptionalClient;

                }
                default:
                    System.out.println("Veuillez choisir une option valide");
                    break;
            }


        return OptionalClient;



    }

    public static void menu() {
        System.out.println("========= premierment creer un nouveau client ou choisir un client existant =========");
        System.out.println("==================================== Menu client =================================\n" +
                            "1. chercher un client existant :\n" +
                            "2. Ajouter un nouveau client :\n" +
                            "Veuillez choisir une option: \n");
    }

    public Optional<Client> store(Client client) {
        Optional<Client>  OptionalClient = Optional.ofNullable(client);
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
            return OptionalClient;
        }else{
            System.out.println("Erreur lors de la creation du client vuillez creer le client de nouveau.");
            return OptionalClient;
        }
    }

    public Optional<Client> findByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du client : ");
        String nomClient = scanner.nextLine();

        Optional<Client> clientOptional= clientService.findByName(nomClient);
        if(clientOptional.isPresent()){
            System.out.println("Client trouver !");
            System.out.println("Nom du client : " + clientOptional.get().getNom());
            System.out.println("Numero de telephone : " + clientOptional.get().getNumeroTelephone());
            System.out.println("Adresse : " + clientOptional.get().getAddress());
            return clientOptional;
        }else{
            return clientOptional;
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
