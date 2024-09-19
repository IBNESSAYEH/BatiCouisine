package com.BatiCouisine.service;

import com.BatiCouisine.entities.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    boolean store(Client client);
    Optional<Client> findById(int id);
    boolean update(int id, Client client);
    boolean destroy(int id);
    HashMap<String, Client> findAll();
}
