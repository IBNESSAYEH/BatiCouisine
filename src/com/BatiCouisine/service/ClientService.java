package com.BatiCouisine.service;

import com.BatiCouisine.entities.Client;

import java.util.HashMap;
import java.util.Optional;

public interface ClientService {
    int store(Client client);
    Optional<Client> findByName(String nomClient);
    boolean update(int id, Client client);
    boolean destroy(int id);
    HashMap<String, Client> findAll();
}
