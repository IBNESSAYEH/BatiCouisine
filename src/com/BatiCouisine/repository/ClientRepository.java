package com.BatiCouisine.repository;

import com.BatiCouisine.entities.Client;

import java.util.HashMap;
import java.util.Optional;

public interface ClientRepository {

    int store(Client client);
    HashMap<String, Client> retrieveAll();
    void update(int id, Client client);
    void destroy(int id);
    Optional<Client> findById(int id);

}
