package com.BatiCouisine.service;

import com.BatiCouisine.entities.Client;

import java.util.List;
import java.util.Optional;

interface ClientService {
    void store(Client client);
    Optional<Client> findById(int id);
    void update(int id, Client client);
    void destroy(int id);
    List<Client> findAll();
}
