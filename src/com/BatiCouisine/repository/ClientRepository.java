package com.BatiCouisine.repository;

import com.BatiCouisine.entities.Client;

import java.util.HashMap;

public interface ClientRepository {

    int store(Client client);
    HashMap<String, Client> retrieveAllClients();
    void update(int id, Client client);
    void destroy(int id);

}
