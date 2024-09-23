package com.BatiCouisine.service.Implementation;

import com.BatiCouisine.entities.Client;
import com.BatiCouisine.repository.ClientRepository;
import com.BatiCouisine.service.ClientService;

import java.util.HashMap;
import java.util.Optional;

public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImp(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public int store(Client client) {
        if (client == null || client.getNom() == null || client.getNom().isEmpty()) {
            System.out.println("les donnees du client sont invalides");
            return 0;
        }
        try {
            return clientRepository.store(client);
        } catch (Exception e) {
            System.err.println("Error pendant la creation du client: " + e.getMessage());
            return 0;
        }
    }

    public boolean update(int id, Client client) {
        if (client == null || client.getNom() == null || client.getNom().isEmpty()) {
            System.out.println("les donnees du client sont invalides");
            return false;
        }
        try {
            clientRepository.update(id, client);
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du client : " + e.getMessage());
            return false;
        }
    }


    public boolean destroy(int id) {
        try {
            clientRepository.destroy(id);
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du client: " + e.getMessage());
            return false;
        }
    }


    public Optional<Client> findByName(String nomClient) {
        try {
            return clientRepository.findByName(nomClient);
        } catch (Exception e) {
            System.err.println("Erreur pendant la recherche du cloient: " + e.getMessage());
            return Optional.empty();
        }
    }

    public HashMap<String, Client> findAll() {
        try {
            return clientRepository.retrieveAll();
        } catch (Exception e) {
            System.err.println("Erreur pendant fetching les donnes: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
